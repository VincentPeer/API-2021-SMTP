package ch.heigvd.api.model.mail;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private final List<Person> victims = new ArrayList<>();

    public void addPerson(Person p) {
        victims.add(p);
    }
    public List<Person> getVictims() {
        return victims;
    }
    public void extractPerson(int index) {
        victims.remove(index);
    }

}
