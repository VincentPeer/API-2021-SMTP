package ch.heigvd.api.model.prank;

/**
 * Configuration des pranks, à savoir le nombre de groupe et les fichiers concernants les victimes et les pranks à
 * envoyer.
 */
public class PrankConfig {

    private int NB_GROUPE;
    private String FILE_VICTIMS_PATH;
    private String FILE_MESSGAGES_PATH;

    public PrankConfig(int n, String victimsFilname, String messageFilename) {
        NB_GROUPE = n;
        FILE_VICTIMS_PATH = victimsFilname;
        FILE_MESSGAGES_PATH = messageFilename;
    }

    public void setNbGroupe(int n) {
        NB_GROUPE = n;
    }

    public void setVictimsFilename(String filename) {
        FILE_VICTIMS_PATH = filename;
    }

    public void setMessageFilename(String filename) {
        FILE_MESSGAGES_PATH = filename;
    }

    public int getNbGroupe() {
        return NB_GROUPE;
    }

    public String getVictimsFilename() {
        return FILE_VICTIMS_PATH;
    }

    public String getMessageFilename() {
    return FILE_MESSGAGES_PATH;
    }

}
