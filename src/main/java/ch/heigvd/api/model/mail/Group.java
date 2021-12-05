package ch.heigvd.api.model.mail;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final List<Person> people;

    public Group() {
        people = new ArrayList<>();
    }

    public int size() {
        return people.size();
    }
    public boolean isEmpty() { return size() == 0; }

    public void addPerson(Person p) {
        people.add(p);
    }
    public List<Person> getPeople() {
        return people;
    }
    public void extractPerson(int index) {
        try {
            people.remove(index);
        } catch (IndexOutOfBoundsException ex) {

        }
    }

    public Person pop() {
        Person tmp = new Person();
        try {
            if (people.size() > 0) {
                tmp = people.get(0);
                people.remove(0);
            } else
                throw new NullPointerException("Error, list is empty");
        } catch (NullPointerException ex) {
            return null;
        }
            return tmp;
    }

}
