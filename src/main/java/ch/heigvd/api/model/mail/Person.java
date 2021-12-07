package ch.heigvd.api.model.mail;

/**
 * Défini une personne avec son prénom, son nom et son email
 */
public class Person {
    private String email;

    public Person(String email) {
        this.email = email;
    }

    public Person() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }
}
