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

    static final Logger LOG = Logger.getLogger(SmtpSend.class.getName());

    /**
     * Envoit un mail à un ou plusieurs destinataires, le mail doit être complet pour un bon fonctionnement.
     * @param in Flux d'entrée pour la réception des messages venant du serveur
     * @param out Flux de sorties pour l'envoie d'information au sereur
     * @param mail Le mail à envoyer
     * @throws IOException En cas d'erreur de lecture/écriture sur un flux, une exception est levée
     */
    void send(BufferedReader in, BufferedWriter out, Mail mail) throws IOException {
        final String EOL = "\r\n"; // Fin de ligne
        final String ERREUR = "Erreur de communication avec le serveur";

        LOG.log(Level.INFO, in.readLine()); // Lecture du message de bienvenue du serveur
        out.write("EHLO mock" + EOL);
        out.flush();

        LOG.log(Level.INFO, "*** Response sent by the server: ***");
        String line = in.readLine();
        while((line.startsWith("250-"))) {
            LOG.log(Level.INFO, line);
            line = in.readLine();
        }

        // Informe l'expéditeur du mail
        out.write("MAIL FROM:" + mail.getSender().getEmail() + EOL);
        out.flush();

        if(!in.readLine().endsWith("Ok"))
            throw new IOException(ERREUR);

        // Informe les destinataires du mail
        for(int i = 0; i < mail.getReceivers().size(); ++i) {
            out.write("RCPT TO:" + mail.getReceivers().get(i).getEmail() + EOL);
            out.flush();

            if(!in.readLine().endsWith("Ok"))
                throw new IOException(ERREUR);
        }

        // Informe de la saisie à venir du corps du message
        out.write("DATA" + EOL);

        // Précise le charset souhaité et l'encodage afin d'avoir la visibilité des caractères spéciaux
        out.write(  "Content-Type: text/plain; charset=UTF-8" + EOL +
                        "Content-Transfer-Encoding: quoted-printable" + EOL);
        out.flush();

        if(!in.readLine().startsWith("354"))
            throw new IOException(ERREUR);

        // Informe le mail de qui est l'émetteur
        out.write("FROM: " + mail.getSender().getEmail() + EOL);

        // Informe les destinataires du mail
        StringBuilder recipients = new StringBuilder("TO: ");
        for(int i = 0; i < mail.getReceivers().size(); ++i)
            recipients.append(mail.getReceivers().get(i).getEmail() + (i != mail.getReceivers().size() - 1 ? "," : ""));

        out.write("Subject: " + mail.getSubject() + EOL);

        out.write(recipients + EOL);
        out.flush();

        // Envoie d'une ligne qui sépare l'entête du corps du message
        out.write(EOL);
        out.flush();

        // Envoie du corps du message
        out.write(mail.getText() + EOL + "." + EOL);
        out.flush();

        if(!in.readLine().endsWith("Ok"))
            throw new IOException(ERREUR);

        // Fin de la communication avec le serveur
        out.write("QUIT" + EOL);
        out.flush();

        LOG.log(Level.INFO, in.readLine()); // Message de fin

    }
}
