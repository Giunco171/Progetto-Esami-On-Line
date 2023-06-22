package service;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Timestamp;
import proto.*;
import proto.EsamiOnLine.*;
import io.grpc.stub.*;
import java.util.*;

import java.io.File;

import static utility.utilityMethods.*;

public class GestioneEsamiOnLineImpl extends GestioneEsamiOnLineGrpc.GestioneEsamiOnLineImplBase{
    private static Map<Appello,List<Studente>> appelliPrevisti= Collections.synchronizedMap(new HashMap<Appello,List<Studente>>());

    public GestioneEsamiOnLineImpl()
    {
        super();
        recuperaPersistenzaAppelliPrevisti();
    }

    /**
     * Restituiamo una deep copy degli appeli previsti
     *
     * @return mappa che associa ad un appello la lista degli studenti prenotati
     * @see Appello
     * @see Studente
     */
    public static HashMap<Appello,LinkedList<Studente>> getAppelliPrevisti()
    {
        HashMap<Appello, LinkedList<Studente>> copy = new HashMap<>();

        synchronized(appelliPrevisti) {
            for (Appello appello : appelliPrevisti.keySet()) {
                List<Studente> studenti = appelliPrevisti.get(appello);
                LinkedList<Studente> studentiCopy = new LinkedList<>(studenti);  //Studente non è immutabile però contiene come attributi un int ed una stringa, perciò è come se lo fosse
                copy.put(appello, studentiCopy);
            }
        }
        return copy; //deep-copy
    }

    /**
     * Ci permetti di memorizzare in memoria centrale un appello.
     *
     * @param appello
     * @return esito della memorizzazione
     */
    public static boolean setAppello(Appello appello)
    {
        if(isAppelloPrenotabile(appello) && ! isAppelloPresente(appello))
        {
            appelliPrevisti.put(appello, Collections.synchronizedList(new LinkedList<Studente>()));
            return true;
        }else{
            return false;
        }
    }

    /**
     * Ci dice se un appello è presente, identificandolo solo mediante il nome del corso e non anche la data
     *
     * @param appello
     * @see Appello
     * @return esito della ricerca
     */
    public static boolean isAppelloPresente(Appello appello){
        boolean flag=false;
        synchronized(appelliPrevisti)
        {
            for(Appello appelloPrevisto : appelliPrevisti.keySet())
            {
                if(appelloPrevisto.getNomeCorso().equals(appello.getNomeCorso())) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }//isAppelloPresente

    /**
     * Permette di popolare la mappa delle prenotazioni prendendo i dati dalla memoria secondaria
     *
     */
    private void recuperaPersistenzaAppelliPrevisti() //viene richiamato dal costruttore quindi non c'è bisogno di utilizzare blocchi synchronizzati
    {
        File folder = new File("fileJsonPrenotazioni"); // Specifica il percorso della cartella
        if (folder.isDirectory()) {
            File[] files = folder.listFiles(); // Restituisce un array di oggetti File presenti nella cartella

            if (files != null) {
                for (File file : files) { //ogni file contiene le coppie <appello,studente>, ma appello è fisso per ogni file
                    if (file.isFile()) {
                        Prenotazioni prenotazioni=fromJsonFilePrenotazioni(file.getPath());
                        List<Prenotazione> listaPrenotazioni = prenotazioni.getPrenotazioniList();
                        for(Prenotazione prenotazione : listaPrenotazioni)
                        {
                            Appello appello=prenotazione.getAppello();
                            Studente studente=prenotazione.getStudente();
                            if(appelliPrevisti.containsKey(appello))
                            {
                                appelliPrevisti.get(appello).add(studente);
                            }else{
                                appelliPrevisti.put(appello, Collections.synchronizedList(new LinkedList<Studente> ()));
                                appelliPrevisti.get(appello).add(studente);
                            }//if appelliPrevisti.containsKey(appello)
                        }
                    }
                }//for
            }
        }//if esterno
    }//recuperaPersistenzaAppelliPrevisti

    /**
     * Restituisce la lista degli appelli previsti al client.
     *
     * @param request parametro che ha il solo scopo di fare da place holder, come se non prendesse alcun parametro
     * @param responseObserver passiamo il risultato per riferimento tramite questo oggetto
     */
    @Override
    public void getAppelli(Empty request, StreamObserver<Appelli> responseObserver )
    {
        Appelli appelliRet=null;
        synchronized(appelliPrevisti) { //addAllAppelli usa un iteratore, dunque è bene usare un lock nativo perchè le richieste sono gestite in multi thread
            appelliRet = Appelli.newBuilder().addAllAppelli(appelliPrevisti.keySet()).build();
        }
        responseObserver.onNext(appelliRet);
        responseObserver.onCompleted();
    }//getAppelli

    /**
     * Se possibile, salviamo la prenotazione di un utente su un determinato appello
     *
     * @param prenotazione
     * @see Prenotazione
     * @param responseObserver passiamo il risultato per riferimento tramite questo oggetto
     */
    @Override
    public void setPrenotazione(Prenotazione prenotazione, StreamObserver<Esito> responseObserver) {
        Appello appello=prenotazione.getAppello();
        Studente studente=prenotazione.getStudente();
        Esito esito = Esito.newBuilder().build();

        esito=esito.toBuilder().setEsito(BoolValue.newBuilder().setValue(false).build())
                .build();
        synchronized(appelliPrevisti) {
            if (appelliPrevisti.containsKey(appello)) {
                if ( ! (appelliPrevisti.get(appello).contains(studente)) && isAppelloPrenotabile(appello)) {
                    appelliPrevisti.get(appello).add(studente);
                    esito=esito.toBuilder().setEsito(BoolValue.newBuilder().setValue(true).build())
                            .build();
                }
            }
        }//synch

        responseObserver.onNext(esito);
        responseObserver.onCompleted();
    }//setPrenotazione

    /**
     * Restituisce la lista delle domande di un dato esame.
     *
     * @param prenotazione
     * @see Prenotazione
     * @param responseObserver passiamo il risultato per riferimento tramite questo oggetto
     */
    @Override
    public void getDomande(Prenotazione prenotazione, StreamObserver<Domande> responseObserver) {
        Appello appello=prenotazione.getAppello();
        Studente studente=prenotazione.getStudente();
        LinkedList<Domanda> listaDomande=new LinkedList<>();

        if(appelliPrevisti.containsKey(appello)
                && isAppelloValido(appello)
                && appelliPrevisti.get(appello).contains(studente))
        {   //se lo studente si è prenotato
            Domande domande=fromJsonFileDomande("fileJsonDomande/"+appello.getNomeCorso()+".json");

            List<Domanda> lista = domande.getDomandeList();
            for(Domanda domanda : lista)
            {
                listaDomande.add(domanda);
            }
        }else{
            //se non si è prenotato restituiamo una lista vuota
        }

        Domande domande = Domande.newBuilder().addAllDomande(listaDomande).build();
        responseObserver.onNext(domande);
        responseObserver.onCompleted();
    }//getDomande

    /**
     * riceve ed analizza le risposte date dall'untente client
     *
     * @param sottomissione
     * @see Sottomissione
     * @param responseObserver passiamo il risultato per riferimento tramite questo oggetto
     */
    @Override
    public void setRisposte(Sottomissione sottomissione, StreamObserver<Modulo> responseObserver) {
        Appello appello=sottomissione.getAppello();
        List<Risposta> listaRisposte=sottomissione.getRisposte().getRisposteList();

        Risposte risposte=fromJsonFileRisposte("fileJsonRisposte/"+appello.getNomeCorso()+".json");
        List<Risposta> listaRisposteCorrette = risposte.getRisposteList();
        int punteggio=0;
        for(Risposta rispostaData : listaRisposte)
        {
            for(Risposta rispostaCorretta : listaRisposteCorrette)
            {
                if(rispostaData.getTestoDomanda().equals(rispostaCorretta.getTestoDomanda()))
                {
                    if(rispostaData.getRisposta().isEmpty()) //se non si specifica il campo string del message allora gprc la definisce come stringa vuota e non null
                    {
                    punteggio-=1;
                    }else if(rispostaData.getRisposta().equals(rispostaCorretta.getRisposta()))  //se la risposta è corretta
                    {
                    punteggio+=3;
                    }else{ //se la risposta è sbagliata si danno 0 punti
                    punteggio+=0; //giusto per rendere più leggibile il metodo, ovviamente questo comando è inutile.
                    }//if controllo risposta omessa,sbagliata,corretta
                    break; //è inutile confrontare le altre risposte, passiamo avanti
                }//if esterno
            }//for interno
        }//for esterno

        appelliPrevisti.get(appello).remove(sottomissione.getStudente()); //una volta sostenuto l'appello lo studente viene rimosso dall'elenco dei prenotati, così da evitare che lo studente possa risostenere l'esame nel breve periodo visto che gli vengono restituite le risposte corrette.


        Modulo modulo = Modulo.newBuilder().setPunteggio(punteggio).setRisposteEsatte(risposte).build();
        responseObserver.onNext(modulo);
        responseObserver.onCompleted();
    }//setRisposte

    private boolean isAppelloValido(Appello appello) //true se l'appello non è scaduto
    {
        Timestamp timestamp=appello.getDataInMillis();
        final int trentaMin = 1000 * 60 * 30;
        if((timestamp.getSeconds() * 1000)-System.currentTimeMillis()< trentaMin &&
                (timestamp.getSeconds() * 1000)-System.currentTimeMillis()>0) //se l'appello a cui vogliamo partecipare (ottenere le domande) è scaduto oppure siamo in anticipo di più di 30 minuti allora restituiamo false
            return true;
        else
            return false;
    }//isAppelloValido

    private static boolean isAppelloPrenotabile(Appello appello)
    {
        Timestamp timestamp=appello.getDataInMillis();
        final int trentaMin = 1000 * 60 * 30;
        if((timestamp.getSeconds() * 1000)-System.currentTimeMillis()> trentaMin) //se l'appello a cui vogliamo prenotarci scade tra più di 30 minuti allora dà true
            return true;
        else
            return false;
    }//isAppelloPrenotabile
}
