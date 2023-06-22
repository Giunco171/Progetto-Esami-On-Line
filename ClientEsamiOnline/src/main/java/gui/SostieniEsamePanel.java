package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
/**
 * Interfaccia grafica usata per far sostenere un appello ad un utente.
 *
 */
public class SostieniEsamePanel extends JPanel {
    private int count=0; //numero progressivo che indica la domanda corrente

    private Object monitor;

    private String[] domandaRisposta=new String[2]; //ogni volta che una domanda viene selezionata ne estraiamo questo valore dall'esterno
    private JPanel grid;

    private JButton confermaRispostaButton;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4; //questo radioButton gestisce l'opzione non data

    private ButtonGroup buttonGroup;

    private JLabel numeroDomanda;

    private JTextArea domanda;
    private JScrollPane domandaConBarraDiScorrimento;

    public void setMonitor(Object monitor)
    {
        this.monitor=monitor;
    }
    public SostieniEsamePanel()
    {
        this.setLayout(new BorderLayout());
        grid=new JPanel();
        grid.setLayout(new GridLayout(2,2));
        confermaRispostaButton=new JButton("Conferma risposta");
        this.add(grid, BorderLayout.CENTER);
        this.add(confermaRispostaButton, BorderLayout.SOUTH);

        radioButton1=new JRadioButton();
        radioButton2=new JRadioButton();
        radioButton3=new JRadioButton();
        radioButton4=new JRadioButton();
        buttonGroup=new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        grid.add(radioButton1);
        grid.add(radioButton2);
        grid.add(radioButton3);
        grid.add(radioButton4);


        numeroDomanda=new JLabel();
        this.add(numeroDomanda, BorderLayout.WEST);

        domanda=new JTextArea();

        domandaConBarraDiScorrimento=new JScrollPane(domanda);
        domandaConBarraDiScorrimento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(domandaConBarraDiScorrimento, BorderLayout.NORTH);

        radioButton1.addActionListener(new AzioneRadioButton());
        radioButton2.addActionListener(new AzioneRadioButton());
        radioButton3.addActionListener(new AzioneRadioButton());
        radioButton4.addActionListener(new AzioneRadioButton());

        confermaRispostaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                synchronized (monitor) {
                    monitor.notify(); // Risveglia il thread in sleep
                    }
                }
            });//azioen button
    }

    public void setNumeroDomanda()
    {
        count++;
        numeroDomanda.setText("Domanda numero:\n"+count);
    }

    public void setDomanda(String domanda)
    {
        this.domanda.setText(domanda);
        domandaRisposta[0]=domanda;
    }

    public void setRadioButtons(String[] risposte)
    {
        radioButton1.setText(risposte[0]);
        radioButton2.setText(risposte[1]);
        radioButton3.setText(risposte[2]);
        radioButton4.setText("Ignora domanda");
        domandaRisposta[1]="Ignora domanda"; //di default si sceglie di ignorare la domanda
    }

    public String[] getRisposta()
    {
        return this.domandaRisposta;
    }

    private class AzioneRadioButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton source = (JRadioButton) e.getSource();
            String text = source.getText();
            domandaRisposta[1]=text;
        }
    }
}
