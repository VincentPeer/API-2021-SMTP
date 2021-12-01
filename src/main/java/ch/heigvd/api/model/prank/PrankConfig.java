package ch.heigvd.api.model.prank;

public class PrankConfig {

    private int NB_GROUPE = 3;
    private String FILE_VICTIMS_PATH =  "config/victims";
    private String FILE_MESSGAGE_PATH =  "config/messages";

    public PrankConfig() {}
    public PrankConfig(int n, String victimsFilname, String messageFilename) {
        NB_GROUPE = n;
        FILE_VICTIMS_PATH = victimsFilname;
        FILE_MESSGAGE_PATH = messageFilename;
    }

    public void setNbGroupe(int n) {
        NB_GROUPE = n;
    }

    public void setVictimsFilename(String filename) {
        FILE_VICTIMS_PATH = filename;
    }

    public void setMessageFilename(String filename) {
        FILE_MESSGAGE_PATH = filename;
    }

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
