package mediator;

import javax.swing.JComponent;

public interface MediatorIF {
    /**
     * Gestisce una determinata situazione in base all'oggetto che richiama il metodo, passato tramite parametri.
     *
     * @param widget oggetto che ha richiamato il metodo.
     */
    void notify(JComponent widget);
}
