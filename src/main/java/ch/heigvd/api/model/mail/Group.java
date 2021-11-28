package ch.heigvd.api.model.mail;

import java.io.*;

public class Group {
    private String[] vicitms;
    private String sender;
    private String receiver;
    private int nbGroup;

    //public Group(int nbGroup)

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public Group(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        int i = 0;
        String mailAdresse;
        while((mailAdresse = br.readLine())  != null) {
            vicitms[i] = mailAdresse;
            ++i;
        }
    }

    public void makeGroup(int nbGroup) {
        if(vicitms.length < 3 * nbGroup) {
            System.out.println("Pas assez d'adresse mail pour autant de groupe");
            return;
        }


    }


}
