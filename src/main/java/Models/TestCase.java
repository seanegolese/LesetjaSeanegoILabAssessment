package Models;

import java.util.ArrayList;

public class TestCase {
    public String Id;
    public String TestSuiteId;
    public String name;
    public boolean Status;
    public String ExecutionTime;
    public String QC_ID;
    public ArrayList<TestStep> TestSteps = new ArrayList<TestStep>();
}
