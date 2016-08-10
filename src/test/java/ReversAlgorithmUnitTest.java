import Algorithms.CaesarAlgorithm;
import Algorithms.ReversAlgorithm;
import Algorithms.SplitAlgorithm;
import Algorithms.XorAlgorithm;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;

import java.io.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nerianeveem on 10/08/2016.
 */
public class ReversAlgorithmUnitTest {

    ReversAlgorithm da;

    @Mock
    CaesarAlgorithm xorObject;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    File file;
    SplitAlgorithm ca;
    String fileContent="asdfs";
    String tempFilePath;
    @Before
    public void create() throws IOException {

        initMocks(this);
        when(xorObject.decryption()).thenReturn(true);
        when(xorObject.encryption()).thenReturn(true);
        file = testFolder.newFile("test.txt"); //create a temp file
        testFolder.newFolder("encrypted");
        testFolder.newFolder("decrypted");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(fileContent);
        bw.close();
        da = new ReversAlgorithm(new FileInputStream(file),file.getPath());
    //    da.setXorAlgorithm(xorObject);
    }

    @Test
    public void test() throws IOException {
        boolean result = da.encryption();
        boolean result2 = da.decryption();
        assertTrue(result);
        assertTrue(result2);

    }
}
