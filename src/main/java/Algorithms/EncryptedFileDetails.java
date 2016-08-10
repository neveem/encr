package Algorithms;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by nerianeveem on 09/08/2016.
 */
@Data
public class EncryptedFileDetails implements Serializable {
    private String fileExtension;
    private boolean isHaveTwoKeys;
    private byte[] keys;
    private String fileName;
}
