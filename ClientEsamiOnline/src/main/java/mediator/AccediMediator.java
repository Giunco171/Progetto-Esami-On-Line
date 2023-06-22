package mediator;

import proto.EsamiOnLine;

import javax.swing.*;
import proto.EsamiOnLine.*;
import proto.GestioneEsamiOnLineGrpc;
import serverAdapter.*;

public class AccediMediator implements MediatorIF{

    private String host;
    private int port;
    ServerGRPC server;

    private JTextField matricola;
    private JTextField codiceFiscale;

    private JButton loginButton;

    private JFrame loginWindow;
    private JFrame selectWindow;

    private Studente studente;


    private AccediMediator(){};
    public AccediMediator(String host, int port, JButton loginButton, JTextField matricola, JTextField codiceFiscale, JFrame loginWindow, JFrame selectWindow)
    {
        this.port=port;
        this.host=host;
        this.loginButton=loginButton;
        this.matricola=matricola;
        this.codiceFiscale=codiceFiscale;
        this.loginWindow=loginWindow;
        this.selectWindow=selectWindow;
    }
    @Override
    public void notify(JComponent widget) {
        if (widget == matricola || widget == codiceFiscale) {   //nel main va fatto loginButton.setEnabled(false); per farlo funzionare
            loginButton.setEnabled(isCampiValidi());
        }
        if(widget==loginButton) {  //se il widget è il loginButton vuol dire che è stato abilitato
            String matricola = this.matricola.getText().trim();
            String codiceFiscale = this.codiceFiscale.getText().trim();
            studente= Studente.newBuilder().setCodiceFiscale(codiceFiscale).setMatricola(Integer.parseInt(matricola)).build();

            //studente.toBuilder().setCodiceFiscale(codiceFiscale).setMatricola(Integer.parseInt(matricola)).build();
            server=new ServerGRPC();
            server.apriSocket(host, port);
            switchWindow();
        }
    }//notify

    public Studente getStudente()
    {
        return this.studente;
    }

    /**
     * Ricaviamo il proxy STUD con cui interagire con il server.
     *
     * @return oggetto proxy con cui interagire per comunicare con il server
     */
    public GestioneEsamiOnLineGrpc.GestioneEsamiOnLineBlockingStub getBlockingStub()
    {
        return server.getBlockingStub();
    }

    /**
     * restituisce l'oggetto server che fa da adapter ai metodi dello STUB
     *
     * @return ServerGRPC
     * @see ServerGRPC
     */
    public ServerGRPC getServer()
    {
        return server;
    }
    private boolean isCampiValidi() {
        String matricola = this.matricola.getText().trim();
        String codiceFiscale = this.codiceFiscale.getText().trim();
        return !matricola.isEmpty() && !codiceFiscale.isEmpty();
    }//isCampivalidi

    private void switchWindow() {
        loginWindow.dispose();
        selectWindow.setVisible(true);
    }
}
