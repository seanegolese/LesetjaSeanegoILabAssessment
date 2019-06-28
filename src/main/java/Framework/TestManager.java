package Framework;

import Models.Screenshot;
import Models.TestCase;
import Models.TestStep;
import Models.TestSuite;
import com.google.gson.Gson;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static Framework.Utils.logger;


public class TestManager {

    public static TestSuite testSuite = new TestSuite();
    public static ArrayList<TestCase> testCases = new ArrayList<TestCase>();
    public static TestCase currentTest;
    public static ArrayList<TestStep> testSteps = new ArrayList<TestStep>();
    public static TestStep testStep;



    static long startTime;

    TestManager() {
        Utils.LogInit();
        setTestSuite();
    }


    public static void setTestSuite() {

         Date date= new Date();
        testSuite.Name =EnvironmentManager.SuiteName;
        testSuite.Id="961cc3ab-18a4-40fc-adeb-aa07aee247de";
        testSuite.ProjectId="c558c42b-040f-48be-a6cb-70e8f0765850";

        startTime=date.getTime();
        testSuite.ExecutionDateTime=date.getTime();

        testSuite.EnvironmentName=EnvironmentManager.Environment;
        testSuite.ScheduledScripts.add("a");

    }

    public static void setTestCase(String Name, String testId) {
        currentTest = new TestCase();
        currentTest.Id="c558c42b-040f-48be-a6cb-70e8f076585"+testSuite.testCases.size();
        currentTest.name = Name;
        currentTest.QC_ID = testId;

    }


    public  static void setTestCaseResults()
    {
        logger.info("Exiting test case "+currentTest.name);
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
        logger.info("Adding "+currentTest.name+" test case to test suite "+testSuite.Name);
        testSuite.testCases.add(currentTest);
    }


    public static  void openReport()
    {
        try {
            File htmlFile = new File("./src/test/Reports/"+EnvironmentManager.reportName+"/index.html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
        catch (Exception e) {

        }
    }

    public static void setSuiteResults()
    {
            try
            {

                int failCount=0;
                for(TestCase test:testSuite.testCases)
                {
                    if(!test.Status)
                    {
                        failCount++;
                    }
                }
                Date date= new Date();


                testSuite.ExecutionTime="";
                testSuite.TestCaseCount=Integer.toString(testSuite.testCases.size());
                testSuite.TotalPass=Integer.toString(testSuite.testCases.size()-failCount);
                testSuite.TotalFail=Integer.toString(failCount);
                testSuite.BrowserName.add(EnvironmentManager.Browser);

                DecimalFormat df1 = new DecimalFormat("0.##");


                testSuite.PassPercentage=df1.format(((Double.valueOf(testSuite.TotalPass)/Double.valueOf(testSuite.testCases.size()))*100));
                testSuite.FailPercentage=df1.format(((Double.valueOf(testSuite.TotalFail)/Double.valueOf(testSuite.testCases.size()))*100));


            }
            catch (Exception e)
            {

            }
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