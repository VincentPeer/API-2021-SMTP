package ch.heigvd.api.model.prank;

import ch.heigvd.api.model.mail.*;
import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Prank {
    Group peopleList;
    List<Group> groups;
    public List<Mail> mails;
    List<String> messages;

    private void initPeopleList(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        String email;
        while((email = br.readLine())  != null) {
            peopleList.addPerson(new Person(email));
        }
    }

    public void makeGroups(int nbGroup, InputStream is) throws IOException {
        groups = new ArrayList<Group>(nbGroup);
        initPeopleList(is);

        // Rempli les groupes
        while(peopleList.size() > 0) {
            for(int j = 0; j < nbGroup; ++j) {
                if(peopleList.size() > 0)
                    groups.get(j).addPerson(peopleList.pop());
            }
        }
    }

    public void makeMails(InputStream mess) throws IOException {
        readMessage(mess);
        mails = new ArrayList<>(groups.size());

        for(int i = 0; i < groups.size(); ++i) {
            // Prend la première personne de chaque groupe comme expéditeur
            mails.get(i).setSender(groups.get(i).getPeople().get(0));

            // Les autres personnes du groupe seront les récéptionnistes
            mails.get(i).setReceiver(groups.get(i).getPeople().subList(1, groups.get(i).size()));

            // Pour chaque groupe, un message est attribué
            mails.get(i).setText(messages.get(i));
        }
    }

    private void readMessage(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        StringBuilder message = new StringBuilder();
        String line = "";
        line = br.readLine();
        while(line != null) {
            while (!line.equals("END_OF_MESSAGE")) {
                message.append(line);
                line = br.readLine();
            }
            messages.add(message.toString());
            line = br.readLine();
        }
    }
}
