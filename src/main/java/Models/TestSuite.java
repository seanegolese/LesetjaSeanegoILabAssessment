package Models;

import java.util.ArrayList;
import java.util.List;

public class TestSuite {
    public String Name;
    public String Id;
    public String ProjectId;
    public String ExecutionDateTime;
    public String ExecutionTime;
    public String TestCaseCount;
    public String TotalPass;
    public String TotalFail;
    public String PassPercentage;
    public String FailPercentage;
    public List<TestCase> testCases = new ArrayList<TestCase>();
    public String EnvironmentName;
    public ArrayList<String> BrowserName = new ArrayList<String>();
    public ArrayList<String> ScheduledScripts = new ArrayList<String>();
    public ArrayList<String> ScriptsNotRun  = new ArrayList<String>();
}
