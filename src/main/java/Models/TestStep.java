package Models;

import java.util.ArrayList;

public class TestStep {
    public int StepNumber;
    public String Description;
    public String Expected;
    public String Actual;
    public ArrayList<Screenshot> Screenshot = new ArrayList<Screenshot>();
    public boolean Status;
}
