package ch.heigvd.api.model.prank;

import ch.heigvd.api.model.mail.Group;
import ch.heigvd.api.model.mail.Mail;

import java.io.*;
import java.util.List;

public class Prank {
    PrankGenerator prank = new PrankGenerator();
    PrankConfig prankConfig;

    public Prank(PrankConfig prankConfig) {
        this.prankConfig = prankConfig;
    }

    public Prank() {}

    public List<Mail> getMails() {
        return prank.mails;
    }

    public List<Group> getGroups() {
        return prank.groups;
    }

    public void makePrank() throws IOException {
        prank.makeGroups(prankConfig.getNbGroupe(), new FileInputStream(prankConfig.getVictimsFilename()));
        prank.makeMails(new FileInputStream(prankConfig.getMessageFilename()));
    }

}
