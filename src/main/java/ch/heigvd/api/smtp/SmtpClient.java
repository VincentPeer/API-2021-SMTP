package ch.heigvd.api.smtp;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.heigvd.api.model.prank.Prank;

public class SmtpClient implements ISmtpClient {
    String addServeur;
    String portServeur;
    static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    public SmtpClient(String add, String port) {
        addServeur = add;
        portServeur = port;
    }


    public void conect() {
        Socket clientSocket = null;
        BufferedWriter out = null;
        BufferedReader in = null;
        Prank prank = new Prank();

        try {
            clientSocket = new Socket(addServeur, Integer.parseInt(portServeur));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            LOG.log(Level.INFO, in.readLine()); // Lit le message de bienvenue du serveur

            out.write("EHLO mock\r\n");
            out.flush();

            LOG.log(Level.INFO, "*** Response sent by the server: ***");
            String line = in.readLine();
            while ((line.startsWith("250-"))) {
                LOG.log(Level.INFO, line);
                line = in.readLine();
            }

            String emailFile = System.in.toString();

            int nbGroupe = System.in.read();
            prank.makeGroups(nbGroupe, new FileInputStream(emailFile));

            String prankFile = System.in.toString();
            prank.makeMails(new FileInputStream(prankFile));


            for(int i = 0; i < nbGroupe; ++i) {
                out.write("MAIL FROM:");
                out.write(prank.mails.get(0).getSender().getEmail() + (i != nbGroupe - 1 ? ", " : ""));
            }

            out.write("MAIL FROM:");
            out.write(prank.mails.get(0).getSender().getEmail());

            line = in.readLine();
            if(!line.endsWith("OK"))
                throw new IOException("Erreur de lecture");



        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (in != null) in.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (clientSocket != null && ! clientSocket.isClosed()) clientSocket.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }

}
