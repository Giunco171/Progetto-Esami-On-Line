package serverAdapter;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Timestamp;

import proto.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.time.*;
import java.util.List;
import java.util.concurrent.*;

import proto.EsamiOnLine.*;

public class ServerGRPC implements Server{
    private ManagedChannel channel;
    private GestioneEsamiOnLineGrpc.GestioneEsamiOnLineBlockingStub blockingStub;

    @Override
    public void apriSocket(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockingStub = GestioneEsamiOnLineGrpc.newBlockingStub(channel);
        //usePlaintext() specifica che la connessione al server sarà non crittografata (non utilizza SSL/TLS)
        //blockingstub è uno stub a chiamate bloccanti
    }
    /**
     * restituiamo lo STUB. Il fatto che sia di tipo "blocking" implica che le chiamate fatte tramite esso avverranno in modo sincrono.
     *
     * @return BlockingStub
     * @see GestioneEsamiOnLineGrpc.GestioneEsamiOnLineBlockingStub
     * @throws Exception Descrizione dell'eccezione che può essere sollevata.
     */

    public GestioneEsamiOnLineGrpc.GestioneEsamiOnLineBlockingStub getBlockingStub()
    {
        return blockingStub;
    }

    @Override
    public void chiudiSocket() throws InterruptedException{
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        //avvia la chiusura del canale gRPC e attende la sua terminazione, ma si interrompe dopo 5 secondi se la terminazione non è avvenuta entro quel periodo di tempo.
        //L'uso di awaitTermination con un timeout è utile per evitare che il client rimanga bloccato indefinitamente nel caso in cui la terminazione del canale non avvenga entro un certo periodo di tempo.
    }

    @Override
    public List<Appello> getAppelli() {
        Empty request = Empty.newBuilder().build();  //la richiesta è senza parametri
        Appelli appelli = blockingStub.getAppelli(request);
        List<Appello> listaAppelli = appelli.getAppelliList();
        return listaAppelli;
    }

    @Override
    public boolean setPrenotazione(Appello appello, Studente studente) {
        Prenotazione prenotazione = Prenotazione.newBuilder()
                .setAppello(appello)
                .setStudente(studente)
                .build();
        Esito esito = blockingStub.setPrenotazione(prenotazione);
        BoolValue risultato = esito.getEsito();
        return risultato.getValue();
    }

    @Override
    public List<Domanda> getDomande(Appello appello, Studente studente) {
        Prenotazione prenotazione = Prenotazione.newBuilder()
                .setAppello(appello)
                .setStudente(studente)
                .build();
        Domande domande = blockingStub.getDomande(prenotazione);
        List<Domanda> listaDomande = domande.getDomandeList();
        return listaDomande;
    }

    @Override
    public Modulo setRisposte(List<Risposta> listaRisposte, Appello appello, Studente studente) {
        Risposte risposte = Risposte.newBuilder().addAllRisposte(listaRisposte).build();
        Sottomissione sottomissione=Sottomissione.newBuilder().setRisposte(risposte).setAppello(appello).setStudente(studente).build();
        return blockingStub.setRisposte(sottomissione);
    }


    /**
     * Ci permette di restituire la data di un appello
     *
     * @param appello
     * @see Appello
     * @return oggetto Instant
     * @see Instant
     */
    public Instant getDataAppello(Appello appello)
    {
        Timestamp timestamp = appello.getDataInMillis();
        Instant instant = Instant.ofEpochMilli(timestamp.getSeconds() * 1000 + timestamp.getNanos() / 1000000);
        return instant;
    }
    /**
     * Ci permette di ricavare la data dell'appello secondo il fuso orario del client
     *
     * @param appello
     * @see Appello
     * @return ZonedDataTime
     */

    public ZonedDateTime getDataAppelloInCurrentTimeZone(Appello appello)
    {
        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = getDataAppello(appello).atZone(systemZone);
        return zonedDateTime;
    }



}
