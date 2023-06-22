import org.junit.jupiter.api.*;

import serverAdapter.*;
import proto.EsamiOnLine.*;
public class TestClass {

    @Test
    public void testPrenotazione()
    {
        ServerGRPC server=new ServerGRPC();
        server.apriSocket("127.0.0.1", 8989);
        Appello appello= Appello.newBuilder().setNomeCorso("Fondamenti").build();
        Studente studente= Studente.newBuilder().setMatricola(220183).setCodiceFiscale("pscgnn01m06c352p").build();
        boolean esito=server.setPrenotazione(appello,studente);
        Assertions.assertEquals(esito,false);
    }

    @Test
    public void testAperturaSocket()
    {
        ServerGRPC server=new ServerGRPC();
        server.apriSocket("127.0.0.1", 8989);
        Assertions.assertNotEquals(server, null);
    }

    @Test
    public void testGetAppelliListaVuota()
    {
        ServerGRPC server=new ServerGRPC();
        server.apriSocket("127.0.0.1", 8989);
        Assertions.assertFalse(server.getAppelli().isEmpty());
    }

    @Test
    public void testGetDomandeListaVuota()
    {
        ServerGRPC server=new ServerGRPC();
        server.apriSocket("127.0.0.1", 8989);
        Appello appello= Appello.newBuilder().setNomeCorso("Fondamenti").build();
        Studente studente= Studente.newBuilder().setMatricola(220183).setCodiceFiscale("pscgnn01m06c352p").build();
        Assertions.assertFalse(server.getDomande(appello, studente).isEmpty());
    }

}
