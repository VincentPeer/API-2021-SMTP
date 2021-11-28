//import ch.heigvd.api.smtp.GetConfig;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;

import ch.heigvd.api.smtp.SmtpClient;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String ... args) {

        final String ADDRESSE = "localhost";
        final String PORT = "8282";


        SmtpClient client = new SmtpClient(ADDRESSE, PORT);
        client.conect();


    }
}
