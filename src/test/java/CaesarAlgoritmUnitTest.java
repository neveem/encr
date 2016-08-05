/**
 * Created by nerianeveem on 19/05/2016.
 */
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

public class CaesarAlgoritmUnitTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    File file;
    CaesarAlgorithm ca;

    @Before
    public void create() throws IOException {
        System.setOut(new PrintStream(outContent)); //all system.out go to outContent buffer

        file = testFolder.newFile("test.txt"); //create a temp file
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("a");
        bw.close();
        ca = new CaesarAlgorithm(new FileInputStream(file),file.getPath());
    }

    @Test
    public void testEncryptionFile() throws IOException {
        boolean result = ca.encryption();
        assertTrue(result);
    }
    @Test
    public void testDecryptionFile() throws IOException {
        boolean result = ca.decryption();
        assertTrue(result);
    }
    @Test
    public void testPrintFile() throws IOException {
        ca.printFile();
        assertEquals("a\r\n", outContent.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

}