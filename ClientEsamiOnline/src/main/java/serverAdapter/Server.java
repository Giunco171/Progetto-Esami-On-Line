package serverAdapter;

import proto.EsamiOnLine.*;


import java.util.*;

public interface Server {

    /**
     * apriSocket() permette di instaurare una connessione, rendendo del tutto trasparente i meccanismi specifici che la instaurano.
     *
     * @param host può essere un indirizzo ip o un hostmane o un alias di un hostname, l'importante è che sia di tipo String.
     * @param port indica la porta su cui vogliamo collegarci, ovvero su cui il server rimane in ascolto, è un intero.
     */
    void apriSocket(String host, int port);

    /**
     * Ci permette di terminare la conenssione e liberare le risorse
     *
     * @throws InterruptedException potremmo riceve un'eccezione se forziamo la chiusura in un momento non adatto
     */
    void chiudiSocket() throws InterruptedException;
    
    /**
     * Descrizione del metodo.
     *
     * @return una lista di Appello.
     * @see Appello
     */
    List<Appello> getAppelli(); //lista vuota se non abbiamo accesso a nessun appello

    /**
     * Ci permette di prenotarci ad un appello
     *
     * @param appello appello su cui vogliamo prenotarci.
     * @param studente credenziali secondo cui volerci prenotare.
     * @return esito della prenotazione.
     */
    boolean setPrenotazione(Appello appello, Studente studente); //false se non è andata a buon fine, true altrimenti

    /**
     * Ci permette di scaricare le domande di un appello a cui siamo prenotati
     *
     * @param appello serve per indicare l'appello a cui voler partecipare
     * @param studente serve per verificare se lo studente è correttamente prenotato
     * @return lista domande. Se la lista è vuota vuol dire che non siamo prenotati a questo appello
     * @see Domanda
     */
    List<Domanda> getDomande(Appello appello, Studente studente); //lista vuota se lo studente non è prenotato a questo appello o se l'appello è scaduto

    /**
     * Serve a sottomettere le risposte dell'appello.
     *
     * @param listaRisposte le risposte selezionate dall'utente
     * @param appello indichiamo a quale appello sono inerenti le risposte.
     * @param studente indichiamo lo studente che ha sostenuto questo esame, dunque non vi è una persistenza tra la richiesta getDomande e setDomande
     * @return Modulo, oggetto con gli esiti dell'appello.
     * @see Modulo
     * @see Risposta
     */
    Modulo setRisposte(List<Risposta> listaRisposte, Appello appello, Studente studente);
}
