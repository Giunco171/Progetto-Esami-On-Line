package commandObserver;

import io.grpc.Server;
import proto.EsamiOnLine;

import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;

import service.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import proto.EsamiOnLine.*;

import java.util.*;

import java.io.*;

public class ShutdownServerCommand implements ActionListener{  //il destinatario di questo command è la memoria secondaria
    Server server;

    public ShutdownServerCommand(Server server)
    {
        this.server=server;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        server.shutdown();
        try {
            server.awaitTermination();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        //memorizzazione dati per garantire la persistenza
        serializzaAppelliPrevisti();
    }

    /**
     * prima di chiudere il server serializziamo tutte le prenotazioni e gli appelli
     *
     */
    static void serializzaAppelliPrevisti() //lo teniamo package tanto non ci sono situazioni in cui un aggiornamento delle persistenze possa fare male
    {
        HashMap<Appello, LinkedList<Studente>> appelliPrevisti=GestioneEsamiOnLineImpl.getAppelliPrevisti();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        deleteFilesInFolder("fileJsonPrenotazioni"); //cancelliamo le vecchie prenotazioni prima di scrivere le nuove, così da evitare che rimangano salvati file corrispondenti a variabili cancellate in memoria centrale

        for(Appello appello : appelliPrevisti.keySet()) //non c'è bisogno del synchronized perchè stiamo manipolando una copia profonda
        {
            LinkedList<Prenotazione> listaPrenotazioni=new LinkedList<>();
            for(Studente studente : appelliPrevisti.get(appello))
            {
                Prenotazione prenotazione=Prenotazione.newBuilder().setAppello(appello).setStudente(studente).build();
                listaPrenotazioni.add(prenotazione);
            }
            Prenotazioni prenotazioni=Prenotazioni.newBuilder().addAllPrenotazioni(listaPrenotazioni).build();
            try (FileWriter writer = new FileWriter("fileJsonPrenotazioni/"+appello.getNomeCorso()+".json")) { //visto che il costruttore FileWriter non prende alcun parametro booleano a true vuol dire che esso sovrascrive i file già esistenti
                gson.toJson(prenotazioni, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//for
    }//serializzaAppelliPrevisti

    /**
     * Prima di salvare i nuovi appelli eliminiamo quelli vecchi, così da non riscrivere appelli scaduti. Potrebbe creare inconsistenza
     *
     */
    private static void deleteFilesInFolder(String folderPath) {
        File folder = new File(folderPath);

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
    }//deleteFilesInFolder

}
