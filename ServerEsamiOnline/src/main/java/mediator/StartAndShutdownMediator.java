package mediator;

import javax.swing.*;

import commandObserver.*;
import io.grpc.Server;

public class StartAndShutdownMediator implements MediatorIF{
    private JButton startButton;
    private JButton shutdownButton;
    private int port;
    private Server server;

    public StartAndShutdownMediator(JButton startButton, JButton shutdownButton, int port)
    {
        this.startButton=startButton;
        this.shutdownButton=shutdownButton;
        this.port=port;
    }
    @Override
    public void notify(JComponent widget) { ////nel main va fatto startButton.setEnabled(true); e shutdownButton.setEnabled(false); per farlo funzionare
        if(widget==startButton)
        {
            startButton.setEnabled(false);
            shutdownButton.setEnabled(true);
            StartServerCommand ssc=new StartServerCommand(port);
            ssc.actionPerformed(null);
            server=ssc.getServer();
        }else{
            startButton.setEnabled(true);
            shutdownButton.setEnabled(false);
            new ShutdownServerCommand(server).actionPerformed(null);
        }
    }
}
