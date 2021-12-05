package ch.heigvd.api.smtp;

import ch.heigvd.api.model.mail.Mail;

import java.io.IOException;

public interface ISmtpClient {
    public void sendMail(Mail mail);
}
