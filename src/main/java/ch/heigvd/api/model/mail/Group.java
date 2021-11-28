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

    public void addPerson(Person p) {
        people.add(p);
    }
    public List<Person> getPeople() {
        return people;
    }
    public void extractPerson(int index) {
        people.remove(index);
    }

    public Person pop() {
        Person tmp = new Person();
        if(people.size() > 0) {
            tmp = people.get(0);
            people.remove(0);
        }
            return tmp;
    }

}
