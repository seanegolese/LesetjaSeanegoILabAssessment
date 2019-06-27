import Framework.BaseTest;
import Framework.DataManager;
import Framework.Utils;
import org.testng.annotations.Test;


public class SubmitApplication extends BaseTest {


    @Test(priority = 1, dataProvider = "Details", dataProviderClass = DataManager.class)
    public void SubmitILabApplication(String name,String email,String message)
    {
    setTestCase("Submit ILab application", "12");
    GotTestEnvironment();

    setTestStep("Click hyperLink Careers", "Navigate to ilab careers page");
    Click("btnCareers","XPATH");

    setTestStep("Select South Africa", "Navigate to a page with a list of South African Vacancies");
    Click("lblScouthAfrica","XPATH");

    setTestStep("Select the first Job Openning", "Navigate to a page with South African Job opennings");
    Click("lnkSouthAfrica","XPATH");

    setTestStep("Click button Apply now", "Page navigate to Application form");
    Click("btnApplyNow","XPATH");

    ScrollDown();
    ScrollUp();


    setTestStep("Enter Name", "Name entered successfully on the text filed");
    ClearAndType("txtName","XPATH", name);


    setTestStep("Enter valid email address","Email address entered successfully");
    ClearAndType("txtEmail","XPATH", email);


    setTestStep("Enter valid cell phone number","Cell phone number entered successfully");
    ClearAndType("txtPhone","XPATH", Utils.getRandomPhoneNumber());


    setTestStep("Enter application message in the text area field","Message entered successfully");
    ClearAndType("txMessage","XPATH", message);

    setTestStep("Click Submit Application button", "Click action performed on submit button");
    Click("btnSubmitApplication","XPATH");

    setTestStep("Verify that Error message 'You need to upload at least one file' is displayed", "Message displayed as Expected");
    TextIs("txtErrorMessage","XPATH", "There are errors in your form.");
    }

}
