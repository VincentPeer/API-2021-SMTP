//import ch.heigvd.api.smtp.GetConfig;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;

import ch.heigvd.api.model.prank.Prank;
import ch.heigvd.api.model.prank.PrankConfig;
import ch.heigvd.api.smtp.SmtpClient;

import java.io.IOException;

public class Main {

    public static void main(String ... args) throws IOException {
        final int NB_GROUPE             = 4;
        final String FILE_VICTIMS_PATH  =  "config/victims";
        final String FILE_MESSGAGE_PATH =  "config/messages";
        final String ADRESSE            =  "localhost";
        final String PORT               =  "25";


        PrankConfig prankConfig = new PrankConfig(NB_GROUPE, FILE_VICTIMS_PATH, FILE_MESSGAGE_PATH);
        Prank prank = new Prank(prankConfig, ADRESSE, PORT);

         if(!prank.makePrank())
             System.out.println("Error to make the prank");

    }
}
