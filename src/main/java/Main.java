import ch.heigvd.api.model.prank.Prank;
import ch.heigvd.api.model.prank.PrankConfig;
import java.io.IOException;

public class Main {

    public static void main(String ... args) throws IOException {
        final int nbGroup             = 4;                 // min 3
        final String victimsFilePath  = "config/victims";  // Le fichier contenant vos victimes
        final String jokesFilePath    = "config/messages"; // Le fichier contenat vos blagues
        final String ADRESSE          = "localhost";       // L'addresse du servereur a utiliser
        final String PORT             = "25";              // Le port du serveur


        PrankConfig prankConfig = new PrankConfig(nbGroup, victimsFilePath, jokesFilePath);
        Prank prank = new Prank(prankConfig, ADRESSE, PORT);

         if(!prank.makePrank())
             System.out.println("Error to make the prank");

    }
}
