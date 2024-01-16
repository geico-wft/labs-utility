package Unit;

import com.revature.Service.CMDService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class CMDServiceTest {
    CMDService cmdService;
    @Before
    public void setUp(){
        cmdService = new CMDService();
    }

    /**
     * When using a basic command such as "echo" the executeCommand method should return appropriate text
     * maven had trouble with powershell, so ignoring this test.
     * @throws IOException
     * @throws InterruptedException
     */

    /**
    @Test
    public void cmdTestEcho() throws IOException, InterruptedException {
//        arrange
        String testCommand = "powershell.exe echo hello";
        String expected = "hello";
//        act
        String actual = cmdService.executeCommand(testCommand, "").trim();
//        assert
        Assert.assertEquals(expected, actual);
    }
    **/
}
