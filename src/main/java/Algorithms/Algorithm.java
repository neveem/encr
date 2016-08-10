package Algorithms;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

/**
 * Created by nerianeveem on 22/07/2016.
 */
@Getter
@Setter
public abstract class Algorithm implements FileS {
    protected String filePath;
    protected FileInputStream file;
    protected byte key;
    protected byte[] keys;
    protected String fileName;
    protected String fileExtension;
    protected boolean keyChanged = false;
    protected String directoryPath;
    protected String decryptionPath;
    protected String encryptionPath;

    public Algorithm(FileInputStream file, String filePath) {
        this.file = file;
        this.filePath = filePath;
        setPaths();
    }

    public abstract boolean decryption();

    public abstract boolean encryption();

    public void setPaths() {
        int sep = filePath.lastIndexOf('\\');
        int dot = filePath.lastIndexOf('.');
        this.fileName = filePath.substring(sep + 1, dot);
        this.directoryPath = filePath.substring(0, sep);
        this.fileExtension = filePath.substring(dot, filePath.length());
        this.encryptionPath = directoryPath + "\\encrypted\\";
        sep = directoryPath.lastIndexOf('\\');
        this.decryptionPath = directoryPath.substring(0, sep) + "\\decrypted\\";
    }

    public void printFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("can't print file!");
        }
    }
}
