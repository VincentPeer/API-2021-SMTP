package ch.heigvd.api.model.mail;

public class Person {
    private final String email;

    public Person(String email) {
        this.email = email;
    }

    public Person() {}

    public String getEmail() {
        return email;
    }

}
