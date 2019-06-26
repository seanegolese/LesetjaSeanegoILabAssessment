package Framework;

import Models.Screenshot;
import Models.TestCase;
import Models.TestStep;
import Models.TestSuite;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class TestManager {

    public static TestSuite testSuite = new TestSuite();
    public static ArrayList<TestCase> testCases = new ArrayList<TestCase>();
    public static TestCase currentTest;
    public static ArrayList<TestStep> testSteps = new ArrayList<TestStep>();
    public static TestStep testStep;


    TestManager() {

        setTestSuite();
    }


    public static void setTestSuite() {
        Utils utils = new Utils();
        testSuite.Name =EnvironmentManager.SuiteName;
        testSuite.Id="961cc3ab-18a4-40fc-adeb-aa07aee247de";
        testSuite.ProjectId="c558c42b-040f-48be-a6cb-70e8f0765850";
        testSuite.ExecutionDateTime=utils.getCurrentTime();

        testSuite.EnvironmentName=EnvironmentManager.Environment;
        testSuite.browser.add(EnvironmentManager.Browser);
        testSuite.ScheduledScripts.add("a");

    }

    public static void setTestCase(String Name, String testId) {
        currentTest = new TestCase();
        currentTest.Id="c558c42b-040f-48be-a6cb-70e8f0765850";
        currentTest.name = Name;
        currentTest.QC_ID = testId;

    }


    public  static void setTestCaseResults()
    {
        for(TestStep step:currentTest.TestSteps)
        {
            if(step.Status)
            {
                currentTest.Status = true;
            }
            else
            {
                currentTest.Status = false;
            }
        }
        testSuite.testCases.add(currentTest);
    }


    public static void setSuiteResults()
    {
        int failCount=0;
        for(TestCase test:testSuite.testCases)
        {
            if(!test.Status)
            {
                failCount++;
            }
        }

        String totalTime="";
try
{
    Utils utils = new Utils();

    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
    Date start = dateFormat.parse(testSuite.ExecutionDateTime);
    Date end = dateFormat.parse(utils.getCurrentTime());
    totalTime =""+(end.getTime()-start.getTime());
}
catch (Exception e)
{

}

        testSuite.ExecutionTime=totalTime;
        testSuite.TestCaseCount=Integer.toString(testSuite.testCases.size());
        testSuite.TotalPass=Integer.toString(testSuite.testCases.size()-failCount);
        testSuite.TotalFail=Integer.toString(failCount);
        testSuite.PassPercentage=Double.toString(((testSuite.testCases.size()-failCount)/testSuite.testCases.size())*100);
        testSuite.FailPercentage=Double.toString((failCount/testSuite.testCases.size())*100);
    }






    public static void setTestStep(String decription, String expected) {
        testStep = new TestStep();
        testStep.StepNumber = currentTest.TestSteps.size() + 1;
        testStep.Description = decription;
        testStep.Expected = expected;
    }


    public static void setStepResults(String actual, String screenshot, boolean status) {
        testStep.Actual = actual;

        Screenshot c = new Screenshot();
        c.Screenshots=screenshot;
        c.Id="083f7c50-3808-47f3-8990-a7904741b48a";
        c.TestStepId="083f7c50-3808-47f3-8990-a7904741b48a";

        testStep.Screenshot.add(c);
        testStep.Status = status;
        currentTest.TestSteps.add(testStep);
    }







    public static void rep() {
        //Write JSON file
        try {
            FileWriter file = new FileWriter("./src/test/Reports/"+EnvironmentManager.reportName+"/Libraries/misc/data.js");
            Gson gson = new Gson();
            String studentJson = gson.toJson(testSuite);
            file.write("settings={\n" +
                    "  \"ServerUrl\": \"http://k/api/testsuite\"\n" +
                    "}\n" +
                    "data="+studentJson);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}