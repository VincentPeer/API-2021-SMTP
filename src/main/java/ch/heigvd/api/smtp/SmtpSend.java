package ch.heigvd.api.smtp;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.heigvd.api.model.prank.Prank;
import ch.heigvd.api.model.prank.PrankConfig;

public class SmtpSend {
    final int NB_GROUPE = 3;
    final String FILE_VICTIMS_PATH =  "config/victims";
    final String FILE_MESSGAGE_PATH =  "config/messages";

    PrankConfig prankConfig = new PrankConfig(NB_GROUPE, FILE_VICTIMS_PATH, FILE_MESSGAGE_PATH);
    Prank prank = new Prank(prankConfig);

    void send(Logger LOG, BufferedReader in, BufferedWriter out) throws IOException {
        prank.makePrank();
        final String EOF = "\r\n";

        LOG.log(Level.INFO, in.readLine()); // Lit le message de bienvenue du serveur
        out.write("EHLO mock" + EOF);
        out.flush();

        LOG.log(Level.INFO, "*** Response sent by the server: ***");
        String line = in.readLine();
        while ((line.startsWith("250-"))) {
            LOG.log(Level.INFO, line);
            line = in.readLine();
        }

        out.write("MAIL FROM:");
        out.write(prank.getMails().get(0).getSender().getEmail());

        line = in.readLine();
        if(!line.endsWith("OK"))
            throw new IOException("Erreur de lecture");



    }
}
