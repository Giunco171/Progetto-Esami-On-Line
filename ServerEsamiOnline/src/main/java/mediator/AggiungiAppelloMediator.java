package mediator;

import javax.swing.*;

import commandObserver.*;
public class AggiungiAppelloMediator implements MediatorIF{
    private JTextField nomeCorso;
    private JTextField data;
    private JButton salvaButton;

    public void setNomeCorsoTextField(JTextField nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public void setDataTextField(JTextField data) {
        this.data = data;
    }

    public void setSalvaButton(JButton salvaButton) {
        this.salvaButton = salvaButton;
    }


    @Override
    public void notify(JComponent widget) {
        if (widget == nomeCorso || widget == data) {   //nel main va fatto salvaButton.setEnabled(false); per farlo funzionare
            salvaButton.setEnabled(isCampiValidi());
        }
        if(widget==salvaButton) {  //se il widget è il salvaButton vuol dire che è stato abilitato
            String nomeCorso = this.nomeCorso.getText().trim();
            String dataAppello = this.data.getText().trim();

            new AggiungiAppelloCommand(nomeCorso,dataAppello).actionPerformed(null); //sfruttiamo un observer come se fosse un command, invece dell'ActionEvent passiamo Null
        }//il fatto di usare un mediator ci impedisce di associare tale observer direttamente al bottone di Java Swing
    }//notify

    /**
     * Controlliamo se il form è stato compilato prima di abilitare il pulsante per far partire l'invio del form
     *
     */
    private boolean isCampiValidi() {
        String nomeCorso = this.nomeCorso.getText().trim();
        String data = this.data.getText().trim();
        return !nomeCorso.isEmpty() && !data.isEmpty();
    }//isCampivalidi

}
