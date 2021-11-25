package ch.heigvd.api.labosmtp;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient {
    String addServeur;
    String portServeur;
    static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    SmtpClient(String hostname, String port) {
        addServeur = hostname;
        portServeur = port;
    }


    public void conect() {
        Socket clientSocket = null;
        BufferedWriter out = null;
        BufferedReader in = null;

        try {
            clientSocket = new Socket(addServeur, Integer.parseInt(portServeur));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            in.readLine(); // Lit le message de bienvenue du serveur

            out.write(malformedHttpRequest);
            out.flush();

            LOG.log(Level.INFO, "*** Response sent by the server: ***");
            String line;
            while ((line = in.readLine()) != null) {
                LOG.log(Level.INFO, line);
            }
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
