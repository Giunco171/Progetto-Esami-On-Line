import mediator.AggiungiAppelloMediator;
import mediator.StartAndShutdownMediator;

import java.io.*;
import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args)
    {
        final int port=8989;

        // Creazione della finestra principale
        JFrame frame = new JFrame("Server EOL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);

        // Creazione dei contenitori e dei loro layout
        JPanel panelEsterno = new JPanel();
        panelEsterno.setLayout(new BorderLayout());
        JPanel panelInterno = new JPanel();
        panelInterno.setLayout(new GridLayout(2,2));

        // Innestiamo i contenitori per ottenere dei layout particolari
        panelEsterno.add(panelInterno, BorderLayout.NORTH);
        frame.add(panelEsterno);

        // Definiamo i componenti che ci servono
        JTextField nomeCorso = new JTextField();
        JTextField data = new JTextField();

        JLabel labelNomeCorso = new JLabel("Nome Corso:");
        JLabel labelData = new JLabel("Data Corso:");

        JButton startButton = new JButton("Avvia");
        JButton shutdownButton = new JButton("Stop");
        JButton aggiungiAppelloButton = new JButton("Aggiungi Appello");

        // Assembliamo i componenti secondo un ordine di interesse
        panelEsterno.add(aggiungiAppelloButton, BorderLayout.SOUTH);
        panelEsterno.add(startButton, BorderLayout.WEST);
        panelEsterno.add(shutdownButton, BorderLayout.EAST);

        panelInterno.add(labelNomeCorso);
        panelInterno.add(nomeCorso);
        panelInterno.add(labelData);
        panelInterno.add(data);

        // Definisco i mediator
        StartAndShutdownMediator mediatorStartStop = new StartAndShutdownMediator(startButton,shutdownButton,port);
        AggiungiAppelloMediator mediatorAggiungiAppelli = new AggiungiAppelloMediator();
        mediatorAggiungiAppelli.setSalvaButton(aggiungiAppelloButton);
        mediatorAggiungiAppelli.setDataTextField(data);
        mediatorAggiungiAppelli.setNomeCorsoTextField(nomeCorso);

        // Associamo ogni subject (button) al proprio listener (mediator) tramite le lambda expression
        startButton.addActionListener( dull -> mediatorStartStop.notify(startButton));
        shutdownButton.addActionListener( dull -> mediatorStartStop.notify(shutdownButton));
        aggiungiAppelloButton.addActionListener( dull -> mediatorAggiungiAppelli.notify(aggiungiAppelloButton));
        data.addActionListener( dull -> mediatorAggiungiAppelli.notify(data));
        nomeCorso.addActionListener( dull -> mediatorAggiungiAppelli.notify(nomeCorso));

        // Disabilitiamo i Button che vanno disabilitati
        aggiungiAppelloButton.setEnabled(false);
        startButton.setEnabled(true);
        shutdownButton.setEnabled(false);

        frame.setVisible(true);
    }

}
