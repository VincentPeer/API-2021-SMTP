package ch.heigvd.api.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe comporte toutes les informations concernant un mail.
 * On a entre autres, les personnes concernées par le mail, le sujet et le corps du message.
 * Chaque attribut possède un getteur/setteur pour respectivement l'accès ou la modification de celui-ci.
 */
public class Mail {
    private Person sender;
    private List<Person> receivers;
    private List<Person> cc;
    private String subject;
    private String text;

    public Mail() {
        receivers = new ArrayList<Person>();
        cc = new ArrayList<Person>();
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public List<Person> getReceivers() {
        return receivers;
    }

    public void setReceiver(List<Person> receivers) {
        this.receivers = receivers;
    }

    public void setCc(List<Person> cc) { this.cc = cc; }

    public List<Person> getCc() { return cc; }

    public void setSubject(String subject) { this.subject = subject; }

    public String getSubject() { return subject; }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() { return text; }

}
