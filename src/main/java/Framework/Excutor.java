package Framework;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Excutor extends ObjectMapping{


    public static WebDriver driver;


    public static void setTestCase(String name,String Id)
    {
        TestManager.setTestCase(name, Id);
    }

    public static void setTestStep(String description,String expected)
    {
        TestManager.setTestStep(description, expected);
    }

    private static void report(String obj)
    {
        String actual ="";
        boolean objectExist = driver.findElement(By.xpath(obj)).isDisplayed();
        if(objectExist)
        {
            actual=TestManager.testStep.Expected+" performed successfully";
        }
        else
            actual = obj + "is not found on the browser";

        TestManager.setStepResults(actual, ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64), objectExist);
    }


    public void DriverInit()
    {
        if(EnvironmentManager.Browser.equals("Chrome"))
        {
            System.setProperty("webdriver.chrome.driver", EnvironmentManager.driverPath+"chromedriver.exe");
            driver = new ChromeDriver();
        }

        if(EnvironmentManager.Browser.equals("IE"))
        {
            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            caps.setCapability("ignoreZoomSetting", true);

            System.setProperty("webdriver.ie.driver", EnvironmentManager.driverPath+"IEDriverServer.exe");
            driver = new InternetExplorerDriver(caps);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }



    public static void GotTestEnvironment()
    {
        driver.navigate().to(EnvironmentManager.url);
    }

    public static void GoToUrl(String url)
    {
        driver.navigate().to(url);
    }


    public static void Click(String obj)
    {
        report(map.get(obj));
        driver.findElement(By.xpath(map.get(obj))).click();
    }

    public static void Type(String obj,String val)
    {
        report(map.get(obj));
        driver.findElement(By.xpath(map.get(obj))).sendKeys(val);
    }

    public static void ClearAndType(String obj,String val)
    {
        report(map.get(obj));
        driver.findElement(By.xpath(map.get(obj))).clear();
        driver.findElement(By.xpath(map.get(obj))).sendKeys(val);
    }


    public static void ScrollDown()
    {

    }

    public static void ScrollUp()
    {

    }

    public static void TextIs(String obj,String text)
    {
        String val = driver.findElement(By.xpath(map.get(obj))).getText();
        boolean sameText;
        if(text.equals(val))
        {
            sameText=true;
        }
        else
        {
            sameText=false;
        }
        TestManager.setStepResults("Text displayed as Expected", ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64),sameText);
    }

    public static String getText(String obj)
    {
        return null;
    }

}
