package ch.heigvd.api.model.prank;

import ch.heigvd.api.model.mail.*;
import java.io.BufferedReader;
import java.io.*;
import java.util.Random;

public class Prank {
    Mail mail = new Mail(); // todo private?
    Group victimsList = new Group();
    Group victims = new Group();

    public void readEmails(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String email;

        while((email = br.readLine())  != null) {
            victimsList.addPerson(new Person(email));
        }

    }

    public void makeGroup() throws IOException {
        if(victimsList.getVictims().size() < 3) {
            System.out.println("Pas assez d'adresse mail pour autant de groupe");
            return;
        }

        // Choisi aléatoirement un expéditeur dans la liste
        Random rand = new Random();
        int indexSender = rand.nextInt(victimsList.getVictims().size());
        mail.setSender(victimsList.getVictims().get(indexSender));

        // Extrait l'expéditeur de la liste des victimes pour ne laisser plus que les récépteurs
        victims = victimsList;
        victims.extractPerson(indexSender);
        mail.setReceiver(victims.getVictims());

        InputStream is = new FileInputStream("config/messages");
        mail.setText(getMessage(is, 5));


    }

    public String getMessage(InputStream is, int nbMessage) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        Random rand = new Random();
        int messageNb = rand.nextInt(nbMessage) + 1;

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
