/**
 * Created by nerianeveem on 19/05/2016.
 */
import Algorithms.Algorithm;
import Algorithms.CaesarAlgorithm;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;

import java.io.*;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CaesarAlgoritmUnitTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    File file;
    CaesarAlgorithm ca;
    String fileContent="asdfs";
    String tempFilePath;
    @Before
    public void create() throws IOException {
        System.setOut(new PrintStream(outContent)); //all system.out go to outContent buffer
        file = testFolder.newFile("test.txt"); //create a temp file
        testFolder.newFolder("encrypted");
        testFolder.newFolder("decrypted");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(fileContent);
        bw.close();
        ca = new CaesarAlgorithm(new FileInputStream(file),file.getPath());
    }

    @Test
    public void testEncryptionFile() throws IOException {
    //    ca.setKey((byte)1);
   //     ca.setKeyChanged(true);
        boolean result = ca.encryption();
        tempFilePath = file.getPath().substring(0,file.getPath().lastIndexOf('\\'));
        testDecryptionFile();
        testPrintFile();
        assertTrue(result);
    }
    @Test
    public void testDecryptionFile() throws IOException {
        File f = new File(tempFilePath+"\\encrypted\\test.encrypted");
        byte key = ca.getKey();
        ca=new CaesarAlgorithm(new FileInputStream(f),ca.getDirectoryPath()+"\\encrypted\\test.encrypted");
        ca.setKey(key);
        ca.setFileExtension(".txt");
        boolean result = ca.decryption();
        assertTrue(result);
    }
    @Test
    public void testPrintFile() throws IOException {
        String s = tempFilePath+"\\decrypted\\test_decrypted.txt";
        File f = new File(s);
        ca = new CaesarAlgorithm(new FileInputStream(f),s);
        ca.printFile();
        assertEquals("start enc.\r\nstart dec.\r\nencryption end\r\n"+fileContent+"\r\n", outContent.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

}