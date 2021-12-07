package ch.heigvd.api.model.prank;

import ch.heigvd.api.smtp.SmtpClient;
import java.io.*;

/**
 * Cette classe lance la création des pranks. Les groupes et les mails sont générés en se référant à la configuration
 * prescrite par la classe PrankConfig. Cette classe contient un client SMTP qui établira la connexion avec le serveur.
 */
public class Prank {
    SmtpClient client;
    PrankGenerator prank = new PrankGenerator();
    PrankConfig prankConfig;

    public Prank(PrankConfig prankConfig, String addr, String port) {
        this.prankConfig = prankConfig;
        this.client = new SmtpClient(addr, port);
    }

    /**
     * Crée les pranks ainsi qu'un client SMTP qui enverra un par un chaque prank.
     * @return True si la création du prank s'est bien déroulée, false sinon
     * @throws IOException En cas d'erreur de lecture/écriture dans un fichier, une erreur est levée
     */
    public boolean makePrank() throws IOException {
        if(!prank.makeGroups(prankConfig.getNbGroupe(), new FileInputStream(prankConfig.getVictimsFilename())))
            return false;
        prank.makeMails(new FileInputStream(prankConfig.getMessageFilename()));

        for(int i = 0 ; i < prankConfig.getNbGroupe(); ++i) {
            client.sendMail(prank.getMails().get(i));
        }
        return true;
    }

}
