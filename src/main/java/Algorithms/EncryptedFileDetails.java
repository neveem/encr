package Algorithms;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by nerianeveem on 09/08/2016.
 */
@Getter
@Setter
public class EncryptedFileDetails implements Serializable { //this class save all important data of file '.bin' file
    private String fileExtension;
    private boolean twoKeys =false;
    private byte[] keys;
    private byte key;
    private String fileName;

    public EncryptedFileDetails(String fileExtension,boolean isHaveTwoKeys, byte[] keys,byte key, String fileName) {
        this.fileExtension = fileExtension;
        this.twoKeys = isHaveTwoKeys;
        this.key = key;
        this.keys = keys;
        this.fileName = fileName;


    }
}
