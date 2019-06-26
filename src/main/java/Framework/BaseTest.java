package Framework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import static Framework.EnvironmentManager.createReportDir;
import static Framework.TestManager.*;


public class BaseTest extends Excutor{

    @BeforeSuite
    public static void FrameworkSetup()
    {

    }

    @BeforeClass
    public static void start()
    {
        TestManager testManager = new TestManager();
        ObjectMapping obj = new ObjectMapping();
        obj.loadObjectFile();
    }



    @BeforeTest
    public void InitializeTest()
    {
        DriverInit();
    }

    @AfterTest
    public void CloseTest()
    {

    }


    @AfterClass
    public static void end()
    {
        driver.quit();
        setTestCaseResults();
        setSuiteResults();
        createReportDir();
        TestManager.rep();


    }



    @AfterSuite
    public static void tearDown()
    {

    }
}
