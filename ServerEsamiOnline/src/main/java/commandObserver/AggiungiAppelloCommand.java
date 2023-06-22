package commandObserver;

import com.google.protobuf.Timestamp;
import proto.EsamiOnLine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static commandObserver.ShutdownServerCommand.serializzaAppelliPrevisti;

public class AggiungiAppelloCommand implements ActionListener {
    private String nomeCorso;
    private String dataAppello;

    public AggiungiAppelloCommand(String nomeCorso, String dataAppello) {
        this.nomeCorso=nomeCorso;
        this.dataAppello=dataAppello;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //specifichiamo un formato secondo cui effettuare il parsing di una stringa per ricavarne la data e poi il timestamp
        String inputFormat = "dd-MM-yyyy HH:mm";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        Date date;
        try {
            date = inputDateFormat.parse(dataAppello);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return;
        }
        long timestampSeconds = date.getTime() / 1000;  // Converte in secondi
        int timestampNanos = 0;  // Imposta i nanosecondi a zero

        //timestamp conforme a quello di google remote procedure call
        Timestamp timestamp = Timestamp.newBuilder()
                .setSeconds(timestampSeconds)
                .setNanos(timestampNanos)
                .build();
        EsamiOnLine.Appello appello= EsamiOnLine.Appello.newBuilder().setNomeCorso(nomeCorso).setDataInMillis(timestamp).build();
        service.GestioneEsamiOnLineImpl.setAppello(appello);
        serializzaAppelliPrevisti();
    }
}
