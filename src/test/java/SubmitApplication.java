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
    Excutor.Click("btnCareers","XPATH");

    Excutor.setTestStep("Select South Africa", "Navigate to a page with a list of South African Vacancies");
    Excutor.Click("lblScouthAfrica","XPATH");

    Excutor.setTestStep("Select the first Job Openning", "Navigate to a page with South African Job opennings");
    Excutor.Click("lnkSouthAfrica","XPATH");


    Excutor.setTestStep("Click button Apply now", "Page navigate to Application form");
    Excutor.Click("btnApplyNow","XPATH");

    Excutor.ScrollUp();


    Excutor.setTestStep("Enter Name", "Name entered successfully on the text filed");
    Excutor.ClearAndType("txtName","XPATH", name);


    Excutor.setTestStep("Enter valid email address","Email address entered successfully");
    Excutor.ClearAndType("txtEmail","XPATH", email);


    Excutor.setTestStep("Enter valid cell phone number","Cell phone number entered successfully");
    Excutor.ClearAndType("txtPhone","XPATH", Utils.getRandomPhoneNumber());


    Excutor.setTestStep("Enter application message in the text area field","Message entered successfully");
    Excutor.ClearAndType("txMessage","XPATH", message);

    Excutor.setTestStep("Click Submit Application button", "Click action performed on submit button");
    Excutor.Click("btnSubmitApplication","XPATH");

    Excutor.setTestStep("Verify that Error message 'You need to upload at least one file' is displayed", "Message displayed as Expected");
    Excutor.TextIs("txtErrorMessage","XPATH", "There are errors in your form.");
    }





    @Test(priority = 2, dataProvider = "Details", dataProviderClass = DataManager.class)
    public void SubmitILabApplicationFail(String name,String email,String message)
    {
        Excutor.setTestCase("Submit ILab application", "12");

        GotTestEnvironment();

        Excutor.setTestStep("Click hyperLink Careers", "Navigate to ilab careers page");
        Excutor.Click("btnCareers","XPATH");

        Excutor.setTestStep("Select South Africa", "Navigate to a page with a list of South African Vacancies");
        Excutor.Click("lblScouthAfrica","XPATH");

        Excutor.setTestStep("Select the first Job Opening", "Navigate to a page with South African Job opennings");
        Excutor.Click("lnkSouthAfrica","XPATH");


        Excutor.setTestStep("Click button Apply now", "Page navigate to Application form");
        Excutor.Click("btnApplyNow","XPATH");

        Excutor.ScrollUp();

        Excutor.setTestStep("Enter Name", "Name entered successfully on the text filed");
        Excutor.ClearAndType("txtName","XPATH", name);


        Excutor.setTestStep("Enter valid email address","Email address entered successfully");
        Excutor.ClearAndType("txtEmail","XPATH", email);


        Excutor.setTestStep("Enter valid cell phone number","Cell phone number entered successfully");
        Excutor.ClearAndType("txtPhone","XPATH", Utils.getRandomPhoneNumber());


        Excutor.setTestStep("Enter application message in the text area field","Message entered successfully");
        Excutor.ClearAndType("txMessage","XPATH", message);

        Excutor.setTestStep("Click Submit Application button", "Click action performed on submit button");
        Excutor.Click("btnSubmitApplication","XPATH");

        Excutor.setTestStep("Verify that Error message 'You need to upload at least one file' is displayed", "Message displayed as Expected");
        Excutor.TextIs("txtErrorMessage","XPATH", "There are errors in your forms.");
    }


}
