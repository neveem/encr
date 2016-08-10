import Algorithms.Algorithm;
import Algorithms.CaesarAlgorithm;
import Algorithms.DoubleAlgorithm;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.*;


import java.io.*;
import java.security.cert.Certificate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nerianeveem on 05/08/2016.
 */
public class DoubleAlgorithmUnitTest {

    @Mock
    CaesarAlgorithm firstAl;
    @Mock
    CaesarAlgorithm secondAl;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    File file;
    CaesarAlgorithm ca;
    String fileContent="asdfs";
    String tempFilePath;


    DoubleAlgorithm da;

    @Before
    public void create() throws IOException {

        initMocks(this);
        when(firstAl.encryption()).thenReturn(true);
        when(firstAl.decryption()).thenReturn(true);
        when(secondAl.encryption()).thenReturn(true);
        when(secondAl.decryption()).thenReturn(true);
        when(firstAl.getKey()).thenReturn((byte)3);
        when(secondAl.getKey()).thenReturn((byte)3);
        file = testFolder.newFile("test.txt"); //create a temp file
        testFolder.newFolder("encrypted");
        testFolder.newFolder("decrypted");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(fileContent);
        bw.close();
        da = new DoubleAlgorithm(new FileInputStream(file),file.getPath());
        da.setAnotherCaesarAlgorithm(firstAl);
        da.setAnotherXorAlgorithm(secondAl);

    }

    @Test
    public void test() throws IOException {
        assertTrue( da.encryption());
        assertTrue( da.decryption());
    }
}
