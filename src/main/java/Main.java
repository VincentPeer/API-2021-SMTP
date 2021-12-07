//import ch.heigvd.api.smtp.GetConfig;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;

import ch.heigvd.api.model.mail.Group;
import ch.heigvd.api.model.mail.Person;
import ch.heigvd.api.model.prank.Prank;
import ch.heigvd.api.model.prank.PrankConfig;

import java.io.IOException;

public class Main {

    public static void main(String ... args) throws IOException {

        final int NB_GROUPE = 5;
        final String FILE_VICTIMS_PATH =  "config/victims";
        final String FILE_MESSGAGE_PATH =  "config/messages";

        PrankConfig prankConfig = new PrankConfig(NB_GROUPE, FILE_VICTIMS_PATH, FILE_MESSGAGE_PATH);
        Prank prank = new Prank(prankConfig);

         if(!prank.makePrank())
             System.out.println("Error to make the prank");

    }
}
