package ch.heigvd.api.model.mail;

import java.util.ArrayList;
import java.util.List;

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

    public void setText(String text) {
        this.text = text;
    }

    public String getText() { return text; }

}
