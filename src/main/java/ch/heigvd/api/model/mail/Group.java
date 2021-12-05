package ch.heigvd.api.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * Crée un groupe de Person en utilisant une ArrayList.
 */
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
            System.out.println(ex + " : Extract index is out of pepole list");
        }
    }

    /**
     * Supprime la première personne du groupe
     * @return La personne qui vient d'être retirée du groupe
     * @NullPointerException Exception levée si on veut pop un groupe vide
     */
    public Person pop() {
        Person tmp = new Person();
        try {
            if (people.size() > 0) {
                tmp = people.get(0);
                people.remove(0);
            } else
                throw new NullPointerException("Can not pop a Person from empty Group");
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
            return tmp;
    }

}
