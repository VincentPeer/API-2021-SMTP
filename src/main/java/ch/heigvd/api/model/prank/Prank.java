package ch.heigvd.api.model.prank;

import ch.heigvd.api.model.mail.*;
import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Prank {
    Group peopleList; // todo private?
    List<Group> groups;
    List<Mail> mails;

    public void initEmails(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        String email;
        while((email = br.readLine())  != null) {
            peopleList.addPerson(new Person(email));
        }
    }

    public void makeGroup(int nbGroup, InputStream is) throws IOException {
        groups = new ArrayList<Group>(nbGroup);
        initEmails(is);

        // Rempli les groupes
        while(peopleList.size() > 0) {
            for(int j = 0; j < nbGroup; ++j) {
                if(peopleList.size() > 0)
                    groups.get(j).addPerson(peopleList.pop());
            }
        }
    }

    public void makeMails() {
        mails = new ArrayList<>(groups.size());

        for(int i = 0; i < groups.size(); ++i) {
            // Prend la première personne de chaque groupe comme expéditeur
            mails.get(i).setSender(groups.get(i).getPeople().get(0));
            // Les autres personnes du groupe seront les récéptionnistes
            mails.get(i).setReceiver(groups.get(i).getPeople().subList(1, groups.get(i).size()));
        }
    }

    public String getMessage(InputStream is, int nbMessage) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        int b = br.read();
        while(b != (messageNb + 48)) {
            b = br.read();
        }

        StringBuilder message = new StringBuilder();
        message.append(br.readLine());
        while(!message.toString().endsWith(".")) {
            message.append(br.readLine());
        }

        return message.toString();
    }

}
