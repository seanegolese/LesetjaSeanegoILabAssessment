package Framework;

import org.testng.annotations.*;

import static Framework.EnvironmentManager.createReportDir;
import static Framework.TestManager.*;


public class BaseTest extends Excutor{

    @BeforeSuite
    @Parameters({ "Environment", "SuiteName", "url", "driverPath", "Browser","dbConnString","username","passwords"})
    public static void setRunTimeData(String Environment, String SuiteName, String url, String driverPath,String Browser,
                                      String dbConnString,String username,String passwords)
    {
        EnvironmentManager.Environment=Environment;
        EnvironmentManager.SuiteName=SuiteName;
        EnvironmentManager.url=url;
        EnvironmentManager.driverPath=driverPath;
        EnvironmentManager.Browser=Browser;
        EnvironmentManager.dbConnString=dbConnString;
        EnvironmentManager.username=username;
        EnvironmentManager.passwords=passwords;
    }


    @BeforeClass(alwaysRun = true)
    public static void start()
    {
        LoadMap();


        TestManager testManager = new TestManager();
        ObjectMapping obj = new ObjectMapping();
        obj.loadObjectFile();
    }




    @BeforeMethod
    public void StartTest()
    {
        DriverInit();
    }



    @AfterMethod
    public void EndTest()
    {
        CloseTestCaseExecution();
        setTestCaseResults();
        openReport();
    }



    @AfterClass(alwaysRun = true)
    public static void end()
    {

    }



    @AfterSuite
    public static void CreateTestSuiteReport()
    {
        setSuiteResults();
        createReportDir();
        TestManager.rep();
    }
}
