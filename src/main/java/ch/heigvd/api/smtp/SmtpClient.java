package ch.heigvd.api.smtp;

import ch.heigvd.api.model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient {
    private final String addServeur;
    private final String portServeur;
    static final Logger log = Logger.getLogger(SmtpClient.class.getName());

    public SmtpClient(String add, String port) {
        addServeur = add;
        portServeur = port;
    }

    public void sendMail(Mail mail) {
        Socket clientSocket = null;
        BufferedWriter out = null;
        BufferedReader in = null;

        try {
            clientSocket = new Socket(addServeur, Integer.parseInt(portServeur));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            SmtpSend smtpSend = new SmtpSend();
            smtpSend.send(in, out, mail);

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException ex) {
            log.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException ex) {
                log.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (in != null) in.close();
            } catch (IOException ex) {
                log.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (clientSocket != null && ! clientSocket.isClosed()) clientSocket.close();
            } catch (IOException ex) {
                log.log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }

}
