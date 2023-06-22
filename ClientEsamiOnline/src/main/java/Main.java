import gui.SostieniEsamePanel;
import mediator.AccediMediator;
import mediator.SelezionaMediator;
import serverAdapter.ServerGRPC;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        final int port=8989;
        final String host="127.0.0.1";

        //TextFields & Labels
        JTextField matricola=new JTextField();
        JTextField codiceFiscale=new JTextField();

        JLabel matricolaLabel=new JLabel("Matricola:");
        JLabel codiceFiscaleLabel=new JLabel("codiceFiscale:");

        JTextField nomeAppello=new JTextField();
        JTextField dataAppello=new JTextField();

        JLabel nomeAppelloLabel=new JLabel("Nome appello:");
        JLabel dataAppelloLabel=new JLabel("Data appello:");

        //TextArea
        JTextArea textArea=new JTextArea();

        //Buttons
        JButton loginButton=new JButton("Accedi");
        loginButton.setEnabled(false);

        JButton getAppelliButton=new JButton("Visualizza Appelli");
        loginButton.setEnabled(true);

        JButton setPrenotazioneButton=new JButton("Salva prenotazione");
        setPrenotazioneButton.setEnabled(false);

        JButton sostieniAppelloButton=new JButton("Sostieni Appello");
        sostieniAppelloButton.setEnabled(false);

        //Frame
        JFrame loginWindow=new JFrame("Client EOL");
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setSize(1080, 720);
        loginWindow.setVisible(true);

        JFrame selectWindow=new JFrame("Client EOL");
        selectWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectWindow.setSize(1080, 720);
        selectWindow.setVisible(false);

        JFrame textAreaWindow=new JFrame("Client EOL");
        textAreaWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        textAreaWindow.setSize(720, 720);
        textAreaWindow.setVisible(false);

        JFrame domandeWindow=new JFrame("Client EOL");
        domandeWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        domandeWindow.setSize(720, 720);
        domandeWindow.setVisible(false);

        //Panel e struttura
        JPanel loginPanel=new JPanel();
        loginWindow.add(loginPanel);
        loginPanel.setLayout(new BorderLayout());

        JPanel loginGrid=new JPanel();
        loginGrid.setLayout(new GridLayout(2,2));

        loginGrid.add(matricolaLabel);
        loginGrid.add(matricola);
        loginGrid.add(codiceFiscaleLabel);
        loginGrid.add(codiceFiscale);

        loginPanel.add(loginGrid, BorderLayout.CENTER);
        loginPanel.add(loginButton, BorderLayout.SOUTH);


        SostieniEsamePanel domandePanel=new SostieniEsamePanel();
        domandeWindow.add(domandePanel);

        textAreaWindow.add(textArea);

        JPanel selezionaGrid=new JPanel();
        selezionaGrid.setLayout(new GridLayout(2,2));
        selezionaGrid.add(nomeAppelloLabel);
        selezionaGrid.add(nomeAppello);
        selezionaGrid.add(dataAppelloLabel);
        selezionaGrid.add(dataAppello);

        JPanel selezionaButtons=new JPanel();
        selezionaButtons.setLayout(new BorderLayout());
        selezionaButtons.add(getAppelliButton, BorderLayout.WEST);
        selezionaButtons.add(setPrenotazioneButton, BorderLayout.CENTER);
        selezionaButtons.add(sostieniAppelloButton, BorderLayout.EAST);

        JPanel selezionaPanel=new JPanel();
        selezionaPanel.setLayout(new BorderLayout());
        selezionaPanel.add(selezionaGrid, BorderLayout.CENTER);
        selezionaPanel.add(selezionaButtons, BorderLayout.SOUTH);
        selectWindow.add(selezionaPanel);

        //Mediators
        AccediMediator accediMediator=new AccediMediator(host, port, loginButton, matricola, codiceFiscale, loginWindow, selectWindow);
        SelezionaMediator selezionaMediator=new SelezionaMediator(accediMediator, textArea, getAppelliButton, textAreaWindow, nomeAppello, dataAppello, domandeWindow, domandePanel, setPrenotazioneButton, sostieniAppelloButton);

        //Observer
        loginButton.addActionListener( dull -> accediMediator.notify(loginButton));
        matricola.addActionListener( dull -> accediMediator.notify(matricola));
        codiceFiscale.addActionListener( dull -> accediMediator.notify(codiceFiscale));

        setPrenotazioneButton.addActionListener( dull -> selezionaMediator.notify(setPrenotazioneButton));
        getAppelliButton.addActionListener(dull -> selezionaMediator.notify(getAppelliButton));
        sostieniAppelloButton.addActionListener(dull -> selezionaMediator.notify(sostieniAppelloButton));
        nomeAppello.addActionListener(dull -> selezionaMediator.notify(nomeAppello));
        dataAppello.addActionListener(dull -> selezionaMediator.notify(dataAppello));

        //Packing
        loginWindow.pack(); //così la finestra si adatta alle proprie dimensioni

    }
}
