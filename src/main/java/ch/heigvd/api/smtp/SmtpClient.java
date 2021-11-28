package ch.heigvd.api.smtp;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        try {
            clientSocket = new Socket(addServeur, Integer.parseInt(portServeur));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            in.readLine(); // Lit le message de bienvenue du serveur

            out.write("EHLO MockMock");
            out.flush();

            LOG.log(Level.INFO, "*** Response sent by the server: ***");
            String line = in.readLine();
            while ((line.startsWith("250-"))) {
                LOG.log(Level.INFO, line);
            }

            out.write("MAIL FROM:");
            // out.write();

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
