package mediator;

import javax.swing.JComponent;

/**
 * Gestisce una determinata situazione in base all'oggetto che richiama il metodo, passato tramite parametri.
 *
 * @param widget oggetto che ha richiamato il metodo.
 */
public interface MediatorIF {
    void notify(JComponent widget);
}
