package ch.heigvd.api.model.prank;

import ch.heigvd.api.smtp.SmtpClient;
import java.io.*;

/**
 * Cette classe crée un ou plusieurs prank(s)
 */
public class Prank {
    SmtpClient client;
    PrankGenerator prank = new PrankGenerator();
    PrankConfig prankConfig;

    public Prank(PrankConfig prankConfig) {
        this.prankConfig = prankConfig;
    }

    public void makePrank() throws IOException {
        prank.makeGroups(prankConfig.getNbGroupe(), new FileInputStream(prankConfig.getVictimsFilename()));
        prank.makeMails(new FileInputStream(prankConfig.getMessageFilename()));

        for(int i = 0 ; i < prankConfig.getNbGroupe(); ++i) {
            client  = new SmtpClient("localhost", "25");
            client.sendMail(prank.getMails().get(i));
        }
    }

}
