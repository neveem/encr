import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by nerianeveem on 22/07/2016.
 */
public class AlgorithmUnitTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    File file;
    CaesarAlgorithm ca;
    @Before
    public void create() throws IOException {
        file = testFolder.newFile("test.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("abc");
        bw.close();
        ca = new CaesarAlgorithm(new FileInputStream(file),file.getPath());
    }

    @Test
    public void testConcatenate() throws IOException {
        boolean result = ca.encryption();
        assertTrue(result);

    }
}
