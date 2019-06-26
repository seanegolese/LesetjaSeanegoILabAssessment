import Framework.BaseTest;
import Framework.DataManager;
import Framework.Excutor;
import Framework.Utils;
import org.testng.annotations.Test;


public class SubmitApplication extends BaseTest {


    @Test(priority = 1, dataProvider = "Details", dataProviderClass = DataManager.class)
    public void SubmitILabApplication(String name,String email,String message)
    {
    Excutor.setTestCase("Submit ILab application", "12");

    GotTestEnvironment();

    Excutor.setTestStep("Click hyperLink Careers", "Navigate to ilab careers page");
    Excutor.Click("btnCareers");

    Excutor.setTestStep("Select South Africa", "Navigate to a page with a list of South African Vacancies");
    Excutor.Click("lblScouthAfrica");

    Excutor.setTestStep("Select the first Job Openning", "Navigate to a page with South African Job opennings");
    Excutor.Click("lnkSouthAfrica");


    Excutor.setTestStep("Clickj button Apply now", "Page navigate to Application form");
    Excutor.Click("btnApplyNow");

    Excutor.ScrollUp();


    Excutor.setTestStep("Enter Name", "Name entered successfully on the text filed");
    Excutor.ClearAndType("txtName", name);


    Excutor.setTestStep("Enter valid email address","Email address entered successfully");
    Excutor.ClearAndType("txtEmail", email);


    Excutor.setTestStep("Enter valid cell phone number","Cell phone number entered successfully");
    Excutor.ClearAndType("txtPhone", Utils.getRandomPhoneNumber());


    Excutor.setTestStep("Enter application message in the text area field","Message entered successfully");
    Excutor.ClearAndType("txMessage", message);

    Excutor.setTestStep("Click Submit Application button", "Click action performed on submit button");
    Excutor.Click("btnSubmitApplication");

    Excutor.setTestStep("Varify that Error message 'You need to upload at least one file' is displayed", "Message displayed as Expected");
    Excutor.TextIs("txtErrorMessage", "There are errors in your form.");
    }

}
