import org.junit.jupiter.api.*;
import proto.EsamiOnLine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utility.utilityMethods.fromJsonFileDomande;

public class TestClass {

    @Test
    public void testSerializzazioneDomande()
    {
        String tmp="{\n" +
                "  \"domande_\": [{\n" +
                "    \"testoDomanda_\": \"Quanto fa 2+2?\",\n" +
                "    \"risposta1_\":\"4\",\n" +
                "    \"risposta2_\":\"5\",\n" +
                "    \"risposta3_\":\"2\"},\n" +
                "    {\n" +
                "      \"testoDomanda_\": \"Quanto fa 2*2?\",\n" +
                "      \"risposta1_\":\"4\",\n" +
                "      \"risposta2_\":\"5\",\n" +
                "      \"risposta3_\":\"2\"},\n" +
                "    {\n" +
                "      \"testoDomanda_\": \"Quanto fa 2^2?\",\n" +
                "      \"risposta1_\":\"4\",\n" +
                "      \"risposta2_\":\"5\",\n" +
                "      \"risposta3_\":\"2\"},\n" +
                "    {\n" +
                "      \"testoDomanda_\": \"Quanto fa 2+0?\",\n" +
                "      \"risposta1_\": \"4\",\n" +
                "      \"risposta2_\":\"5\",\n" +
                "      \"risposta3_\":\"2\"},\n" +
                "    {\n" +
                "      \"testoDomanda_\": \"Quanto fa 2+3?\",\n" +
                "      \"risposta1_\": \"4\",\n" +
                "      \"risposta2_\":\"5\",\n" +
                "      \"risposta3_\":\"2\"},\n" +
                "    {\n" +
                "      \"testoDomanda_\": \"Quanto fa 4-2?\",\n" +
                "      \"risposta1_\": \"4\",\n" +
                "      \"risposta2_\":\"5\",\n" +
                "      \"risposta3_\":\"2\"},\n" +
                "    {\n" +
                "      \"testoDomanda_\": \"Quanto fa 5+0?\",\n" +
                "      \"risposta1_\": \"4\",\n" +
                "      \"risposta2_\":\"5\",\n" +
                "      \"risposta3_\":\"2\"},\n" +
                "    {\n" +
                "      \"testoDomanda_\": \"Quanto fa 22-20?\",\n" +
                "      \"risposta1_\": \"4\",\n" +
                "      \"risposta2_\":\"5\",\n" +
                "      \"risposta3_\":\"2\"},\n" +
                "    {\n" +
                "      \"testoDomanda_\": \"Quanto fa 55-50?\",\n" +
                "      \"risposta1_\": \"4\",\n" +
                "      \"risposta2_\":\"5\",\n" +
                "      \"risposta3_\":\"2\"},\n" +
                "    {\n" +
                "      \"testoDomanda_\": \"Quanto fa log_2(4)?\",\n" +
                "      \"risposta1_\": \"4\",\n" +
                "      \"risposta2_\":\"5\",\n" +
                "      \"risposta3_\":\"2\"}\n" +
                "  ]\n" +
                "}";
        EsamiOnLine.Domande domande=fromJsonFileDomande("fileJsonDomande/"+"Fondamenti"+".json");
        assertEquals(tmp,domande.toString());

    }
}
