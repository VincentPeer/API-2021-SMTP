package ch.heigvd.api.smtp;

import java.io.*;
import java.util.Random;

public class GetPrankConfig {

    private final int NB_GROUPE = 3;
    private final String FILE_VICTIMS_PATH =  "config/victims";
    private final String FILE_MESSGAGE_PATH =  "config/messages";

    public int getNbGroupe() {
        return NB_GROUPE;
    }

    public String getVictimsFilename() {
        return FILE_VICTIMS_PATH;
    }

    public String getMessageFilename() {
    return FILE_MESSGAGE_PATH;
    }


}
