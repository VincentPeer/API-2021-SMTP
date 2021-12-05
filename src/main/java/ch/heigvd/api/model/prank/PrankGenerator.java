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

public class PrankGenerator {
    private Group victimsList = new Group();;
    private List<Group> groups;
    private List<Mail> mails;
    private List<String> messages = new ArrayList<>();

    public List<Mail> getMails () { return mails; }
    public List<Group> getGroups() {
        return groups;
    }

    public void makeGroups(int nbGroup, InputStream victimsStream) throws IOException {
        groups = new ArrayList<>(nbGroup);
        BufferedReader br = new BufferedReader(new InputStreamReader(victimsStream, "UTF-8"));

        // Crée la liste des victimes à partir des emails donnés
        String email;
        while((email = br.readLine())  != null) {
            Person p = new Person(email);
            victimsList.addPerson(p);
        }
        for(int j = 0; j < nbGroup; ++j)
            groups.add(new Group());

        // Rempli les groupes
        while(!victimsList.isEmpty()) { // todo le dernier groupe aura une taille spéciale + gérer nb personne dans  la liste (min 3 par groupe)
            for(int j = 0; j < nbGroup; ++j) {
                if(!victimsList.isEmpty()) // todo 2x la meme condition...
                    groups.get(j).addPerson(victimsList.pop());
            }
        }
    }

    public void makeMails(InputStream mess) throws IOException { // todo les check si assez de message etc
        readMessages(mess);
        mails = new ArrayList<>(groups.size());
        for(int j = 0; j < groups.size(); ++j)
            mails.add(new Mail());

        for(int i = 0; i < groups.size(); ++i) {
            // Prend la première personne de chaque groupe comme expéditeur
            mails.get(i).setSender(groups.get(i).getPeople().get(0));

            // Les autres personnes du groupe seront les récéptionnistes
            mails.get(i).setReceiver(groups.get(i).getPeople().subList(1, groups.get(i).size()));

            // Pour chaque groupe, un message est attribué
            mails.get(i).setText(messages.get(i));
        }
    }

    private void readMessages(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        StringBuilder message = new StringBuilder();
        String line = "";
        while((line = br.readLine()) != null) {
            while (!line.equals("END_OF_MESSAGE")) {
                message.append(line);
                line = br.readLine();
            }
            messages.add(message.toString());
        }
    }
}
