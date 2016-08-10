import Algorithms.MWOAlgorithm;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nerianeveem on 05/08/2016.
 */
public class MWOAlgorithmUnitTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    File file;
    MWOAlgorithm ca;
    String fileContent="aaa";
    String tempFilePath;
    @Before
    public void create() throws IOException {
        System.setOut(new PrintStream(outContent)); //all system.out go to outContent buffer
        file = testFolder.newFile("test.txt"); //create a temp file
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(fileContent);
        testFolder.newFolder("encrypted");
        testFolder.newFolder("decrypted");
        bw.close();
        ca = new MWOAlgorithm(new FileInputStream(file),file.getPath());
    }

    @Test
    public void testEncryptionFile() throws IOException {
        ca.setKey((byte)3);
        ca.setKeyChanged(true);
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
        ca=new MWOAlgorithm(new FileInputStream(f),ca.getDirectoryPath()+"\\encrypted\\test.encrypted");
        ca.setKey(key);
        ca.setFileExtension(".txt");
        boolean result = ca.decryption();
        assertTrue(result);
    }
    @Test
    public void testPrintFile() throws IOException {
        String s = tempFilePath+"\\decrypted\\test_decrypted.txt";
        File f = new File(s);
        ca = new MWOAlgorithm(new FileInputStream(f),s);
        ca.printFile();
        assertEquals("start enc.\r\nstart dec.\r\nencryption end\r\n"+fileContent+"\r\n", outContent.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }


}
