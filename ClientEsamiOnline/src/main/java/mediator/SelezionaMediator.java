package mediator;

import javax.swing.*;

import gui.SostieniEsamePanel;
import proto.GestioneEsamiOnLineGrpc;
import serverAdapter.*;
import proto.EsamiOnLine.*;
import java.util.*;


public class SelezionaMediator implements MediatorIF{
    private ServerGRPC server;
    private Studente studente;
    private JTextField nomeAppello;
    private JTextField dataAppello;

    private JTextArea textArea;

    private JButton getAppelliButton;
    private JButton setPrenotazioneButton;
    private JButton sostieniAppelloButton;

    private JFrame textAreaWindow;
    private JFrame domandeWindow;

    private SostieniEsamePanel domandePanel;

    private AccediMediator accediMediator;

    private SelezionaMediator() {};

    public SelezionaMediator(AccediMediator accediMediator, JTextArea textArea, JButton getAppelliButton, JFrame textAreaWindow, JTextField nomeAppello, JTextField dataAppello, JFrame domandeWindow, SostieniEsamePanel domandePanel, JButton setPrenotazioneButton, JButton sostieniAppelloButton)
    {
        this.accediMediator=accediMediator;
        this.textArea=textArea;
        this.getAppelliButton=getAppelliButton;
        this.textAreaWindow=textAreaWindow;
        this.dataAppello=dataAppello;
        this.nomeAppello=nomeAppello;
        this.domandeWindow=domandeWindow;
        this.domandePanel=domandePanel;
        this.setPrenotazioneButton=setPrenotazioneButton;
        this.sostieniAppelloButton=sostieniAppelloButton;
    }
    @Override
    public void notify(JComponent widget) {
        studente=accediMediator.getStudente();
        server=accediMediator.getServer();
        if (widget == getAppelliButton ) {
            List<Appello> listaAppelli=server.getAppelli();
            textArea.setText(listaAppelliToString(listaAppelli));
            textAreaWindow.setVisible(true);
        }
        if (widget == nomeAppello || widget == dataAppello) {
            setPrenotazioneButton.setEnabled(isCampiValidi());
            sostieniAppelloButton.setEnabled(isCampiValidi());
        }
        if (widget == setPrenotazioneButton)
        {
            String nomeAppello = this.nomeAppello.getText().trim();
            Appello appello=inferisciAppello(nomeAppello);
            Boolean esito=server.setPrenotazione(appello,studente);
            if(appello!=null && esito)
                textArea.setText("Prenotazione avvenuta con successo.");
            else
                textArea.setText("Prenotazione fallita.");
            textAreaWindow.setVisible(true);
        }
        if (widget == sostieniAppelloButton)
        {
            String nomeAppello = this.nomeAppello.getText().trim();
            Appello appello=inferisciAppello(nomeAppello);
            new AppelloHandler(appello).start();
        }

    }//notify

    private Appello inferisciAppello(String nomeAppello)
    {
        List<Appello> listaAppelli=server.getAppelli();
        Appello appello=null;
        for(Appello elem : listaAppelli)  //l'appello è identificato dal nome, se non fosse così il timestamp ci darebbe problemi lato server nell'equals()
        {
            if(elem.getNomeCorso().equals(nomeAppello))
            {
                appello=elem;
                break;
            }
        }//for
        return appello;
    }//inferisciAppello

    private String listaAppelliToString(List<Appello> listaAppelli)
    {
        StringBuilder builder=new StringBuilder();
        for(Appello appello : listaAppelli)
        {
            builder.append(appello.getNomeCorso()+" in data ");
            builder.append(server.getDataAppelloInCurrentTimeZone(appello)+";\n");
        }
        return builder.toString();
    }//listaAppelliToString

    private boolean isCampiValidi() {
        String nomeAppello = this.nomeAppello.getText().trim();
        String dataAppello = this.dataAppello.getText().trim();
        return !nomeAppello.isEmpty() && !dataAppello.isEmpty();
    }//isCampivalidi

    private class AppelloHandler extends Thread{
        private Appello appello;

        public AppelloHandler(Appello appello){
            this.appello=appello;
        }//AppelloHandler

        public void run()
        {
            List<Domanda> domande=server.getDomande(appello,studente); //restituisce una lista vuota se non siamo prenotati o se l'appello non è presente
            Object monitor=new Object(); //usiamo un monitor nativo
            List<Risposta> ret=new LinkedList<Risposta>();
            domandePanel.setMonitor(monitor);

            if(domande.isEmpty())
                return ;

            domandeWindow.setVisible(true);
            try {
                synchronized (monitor) {
                    for (Domanda elem : domande) {
                        domandePanel.setNumeroDomanda();
                        domandePanel.setDomanda(elem.getTestoDomanda());
                        String[] risposte = new String[]{elem.getRisposta1(),
                                elem.getRisposta2(),
                                elem.getRisposta3()};
                        domandePanel.setRadioButtons(risposte);
                        monitor.wait(1000 * 60 * 5); //5 minuti massimo
                        String[] scelte=domandePanel.getRisposta();
                        Risposta tmp=Risposta.newBuilder().setRisposta(scelte[1]).setTestoDomanda(scelte[0]).build();
                        ret.add(tmp);
                    }
                    domandeWindow.setVisible(false);

                    Modulo modulo=server.setRisposte(ret,appello,studente);

                    StringBuilder moduloPrettyPrinting=new StringBuilder();
                    List<Risposta> risposte=modulo.getRisposteEsatte().getRisposteList();
                    int punteggio=modulo.getPunteggio();

                    moduloPrettyPrinting.append("Hai totalizzato "+punteggio+" punti. Le risposte esatte sono:\n");
                    for(Risposta elem : risposte)
                    {
                        moduloPrettyPrinting.append(elem.getTestoDomanda()+" ---> "+elem.getRisposta()+"\n");
                    }
                    textArea.setText(moduloPrettyPrinting.toString());
                    //stampo il modulo nella textArea
                    domandeWindow.dispose();

                }//synchronized
            }catch(InterruptedException excep)
            {
                excep.printStackTrace();
            }//try
        }//run
    }//AppelloHandler

}//SelezionaMediator
