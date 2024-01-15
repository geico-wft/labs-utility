package Unit;

import com.revature.Exception.LabClearException;
import com.revature.Service.CMDService;
import com.revature.Service.FileService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

public class FileServiceTest {
    FileService fileService;
    CMDService mockCMDService;
    @Before
    public void setUp(){
        mockCMDService = Mockito.mock(CMDService.class);
        fileService = new FileService(mockCMDService);
    }
    @Test
    public void createDirThenClearTest() throws IOException, LabClearException {
        File testDir = new File("lab");
        if(testDir.exists()){
//            for some reason, a hanging test directory existed.
            fileService.clear();
        }
        testDir.mkdirs();
        File testFile = new File("lab/test.txt");
        testFile.createNewFile();
        fileService.clear();
        if(testDir.exists()){
            Assert.fail();
        }
    }
    @After
    public void tearDown(){
        File testDir = new File("testDir");
        if(testDir.exists()){
            testDir.delete();
        }
    }
}
