package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import proto.EsamiOnLine.*;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Fornisce metodi di utilità per deserializzare degli specifici oggetti json
*/
public class utilityMethods {

    public static Risposte fromJsonFileRisposte(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(reader, Risposte.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }//fromJsonFilePrenotazioni
    public static Domande fromJsonFileDomande(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(reader, Domande.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }//fromJsonFilePrenotazioni
    public static Prenotazioni fromJsonFilePrenotazioni(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(reader, Prenotazioni.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }//fromJsonFilePrenotazioni

    private <T> T fromJsonFile(String filePath) {
        Gson gson = new Gson();

        try (FileReader fileReader = new FileReader(filePath)) {
            Type tipoGenerico = new TypeToken<T>() {}.getType();
            System.out.println(tipoGenerico);
            return gson.fromJson(fileReader, tipoGenerico);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Il codice sopra utilizza un blocco try-with-resources per assicurarsi che il FileReader venga chiuso correttamente una volta terminata la lettura del file JSON.
        /*
        La sintassi {} dopo new TypeToken<T>() viene utilizzata per creare
        una classe anonima necessaria per mantenere l'informazione di tipo durante
        la compilazione.
        Il metodo getType() restituisce l'oggetto Type che rappresenta il tipo generico
        effettivo. Questo Type viene quindi utilizzato da Gson per deserializzare
        correttamente l'oggetto JSON nel tipo specificato.
         */
        return null;
    }//fromJsonFile
}
