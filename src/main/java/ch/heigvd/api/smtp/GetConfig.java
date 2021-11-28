package ch.heigvd.api.smtp;

import java.io.*;
import java.util.Random;

public class GetConfig {
    Random rand = new Random();


    public String getMessage(InputStream is, int nbMessage) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        Integer messageNb = rand.nextInt(nbMessage) + 1;

        Integer b = br.read();
        while(b != (messageNb + 48)) {
            b = br.read();
        }

        String message = "";
        message += br.readLine();
        while(!message.endsWith(".")) {
            message += br.readLine();
        }

        return message;
    }



}
