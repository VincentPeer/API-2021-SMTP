package ch.heigvd.api.smtp;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.heigvd.api.model.mail.Mail;


/**
 * La classe SmtpSend dialogue avec un serveur SMTP pour l'envoie d'un mail.
 * Il y a une unique fonction send qui effectue la communication.
 * Cette fonction doit être appelée depuis une classe auxiliaire qui établit au préalable la connexion
 * avec le serveur. Nous utilisons des flux d'entrées/sorties pour le dialogue avec le serveur connecté.
 */
public class SmtpSend {

    static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    /**
     *
     * @param in Flux d'entrée pour la réception des messages venant du serveur
     * @param out Flux de sorties pour l'envoie d'information au sereur
     * @param mail Le mail à envoyer
     * @throws IOException En cas d'erreur de lecture/écriture sur un flux, une exception est levée
     */
    void send(BufferedReader in, BufferedWriter out, Mail mail) throws IOException {
        final String EOL = "\r\n";

        LOG.log(Level.INFO, in.readLine()); // Lit le message de bienvenue du serveur
        out.write("EHLO mock" + EOL);
        out.flush();

        LOG.log(Level.INFO, "*** Response sent by the server: ***");
        String line = in.readLine();
        while ((line.startsWith("250-"))) {
            LOG.log(Level.INFO, line);
            line = in.readLine();
        }

        out.write("MAIL FROM:" + mail.getSender().getEmail() + EOL);
        out.flush();

        if(!in.readLine().endsWith("Ok"))
            throw new IOException("Erreur de lecture");

        for(int i = 0; i < mail.getReceivers().size(); ++i) {
            out.write("RCPT TO:" + mail.getReceivers().get(i).getEmail() + EOL);
            out.flush();

            line = in.readLine();
            if(!line.endsWith("Ok"))
                throw new IOException("Erreur de lecture");
        }

        out.write("DATA" + EOL);
        out.flush();

        line = in.readLine();
        if(!line.startsWith("354"))
            throw new IOException("Erreur de lecture");

        out.write(mail.getText() + EOL + "." + EOL);
        out.flush();

        line = in.readLine();
        if(!line.endsWith("Ok"))
            throw new IOException("Erreur de lecture");

        out.write("QUIT" + EOL);
        out.flush();

        LOG.log(Level.INFO, in.readLine()); // Message de fin

    }
}
