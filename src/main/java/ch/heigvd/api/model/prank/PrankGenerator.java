package ch.heigvd.api.model.prank;

import ch.heigvd.api.model.mail.Group;
import ch.heigvd.api.model.mail.Mail;
import ch.heigvd.api.model.mail.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A partir des informations de la classe PrankConfig, la classe PrankGenerator crée les différents groupes
 * dans lesquels on trouvera un expéditeur et des destinataires. Les mails sont également généré et chaque groupe
 * aura un mail correspondant, avec un prank attribué.
 */
public class PrankGenerator {
    private Group victimsList = new Group();
    private List<Group> groups;
    private List<Mail> mails;
    private final List<String> messages = new ArrayList<>();
    static private final int GROUP_MIN_SIZE = 3;
    static private final String END_OF_MESSAGE = "END_OF_MESSAGE";

    public List<Mail> getMails () { return mails; }
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Génère les groupes selon la liste de victime et le nombre de groupe à former.
     * Le nombre d'adresses mail fourni doit être suffisant pour que chaque groupe puisse être composé de 3 personnes
     * au minimum.
     * @param nbGroup Nombre de groupe différent à former
     * @param victimsStream Flux menant au fichier comportant la liste des emails des victimes
     * @return True si la création s'est bien passée, false sinon
     * @throws IOException En cas d'erreur de lecture/écriture dans un fichier, une erreur est levée
     */
    public boolean makeGroups(int nbGroup, InputStream victimsStream) throws IOException {
        groups = new ArrayList<>(nbGroup);
        BufferedReader br = new BufferedReader(new InputStreamReader(victimsStream, "UTF-8"));

        // Crée la liste des victimes à partir des emails donnés
        String email;
        while((email = br.readLine())  != null) {
            Person p = new Person(email);
            victimsList.addPerson(p);
        }
        // Vérification que le nombre d'email est suffisant pour le nombre de groupe
        if(victimsList.size() < GROUP_MIN_SIZE * nbGroup) {
            System.out.println("Error, email list isn't long enough to make " + nbGroup + " groups");
            return false;
        }

        // Initialisation des groupes
        for(int j = 0; j < nbGroup; ++j)
            groups.add(new Group());

        // Rempli les groupes depuis la liste de victime
        while(!victimsList.isEmpty()) {
            for(int j = 0; j < nbGroup; ++j) {
                if(!victimsList.isEmpty())
                    groups.get(j).addPerson(victimsList.pop());
            }
        }
        return true;
    }

    /**
     * Génère un mail propre à chaque groupe
     * @param emailStream Flux menant au fichier contenant la liste des emails
     * @throws IOException En cas d'erreur de lecture, une erreur est levée
     */
    public void makeMails(InputStream emailStream) throws IOException { // todo les check si assez de message etc
        readMessages(emailStream);
        mails = new ArrayList<>(groups.size());

        //Initialisation des mails
        for(int j = 0; j < groups.size(); ++j)
            mails.add(new Mail());

        for(int i = 0; i < groups.size(); ++i) {
            // Prend la première personne de chaque groupe comme expéditeur
            mails.get(i).setSender(groups.get(i).getPeople().get(0));

            // Les autres personnes du groupe seront les récéptionnistes
            mails.get(i).setReceiver(groups.get(i).getPeople().subList(1, groups.get(i).size()));

            // Donne comme sujet du mail, le numéro du prank correspondant
            mails.get(i).setSubject("Prank " + (i + 1));

            // Pour chaque groupe, un message est attribué
            mails.get(i).setText(messages.get(i));
        }
    }

    /**
     * Lecture des messages qui deviendront le corps du mail. Le fichier contenant les messages doit contenir pour
     * chaque fin de message une ligne précise qui annonce la fin d'un message. Cette ligne doit correspondre à la
     * constante END_OF_MESSAGE déclarée comme attribut de cette classe.
     * @param messageStream Flux menant au fichier contenant la liste des emails
     * @throws IOException En cas d'erreur de lecture, une erreur est levée
     */
    private void readMessages(InputStream messageStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(messageStream, "UTF-8"));

        StringBuilder message = new StringBuilder();
        String line = "";
        while((line = br.readLine()) != null) {
            while (!line.startsWith(END_OF_MESSAGE)) {
                message.append(line + "\r\n");
                line = br.readLine();
            }
            messages.add(message.toString());
            message = new StringBuilder();
        }
    }
}
