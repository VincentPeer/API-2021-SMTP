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

    public void send(Logger LOG, BufferedReader in, BufferedWriter out) throws IOException {
        SmtpSend smtpSend = new SmtpSend();
        smtpSend.send(LOG, in, out);
    }


    public void conect() {
        Socket clientSocket = null;
        BufferedWriter out = null;
        BufferedReader in = null;

        try {
            clientSocket = new Socket(addServeur, Integer.parseInt(portServeur));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            send(LOG, in, out);

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
