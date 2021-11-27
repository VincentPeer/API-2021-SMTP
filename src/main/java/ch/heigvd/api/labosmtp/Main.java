package ch.heigvd.api.labosmtp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String ... args) throws IOException {
        InputStream is = new FileInputStream("config/messages");
        GetMessage gm = new GetMessage();
        String mess = gm.getMessage(is, 5);
        System.out.println(mess);
    }
}
