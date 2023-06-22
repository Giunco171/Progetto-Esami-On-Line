package commandObserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.GestioneEsamiOnLineImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartServerCommand implements ActionListener {
    Server server;
    int port;

    public StartServerCommand(int port)
    {
        //this.server=server;
        this.port=port;
    }

    public Server getServer()
    {
        return server;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        server = ServerBuilder.forPort(port).addService(new GestioneEsamiOnLineImpl()).build();
        try {
            server.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
