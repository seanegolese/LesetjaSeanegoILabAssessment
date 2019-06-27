package Framework;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static Framework.Utils.logger;

public class Excutor extends ObjectMapping{


    public static WebDriver driver;


    public static void setTestCase(String name,String Id)
    {
        logger.info("Initializing test case "+name);
        TestManager.setTestCase(name, Id);
    }

    public static void setTestStep(String description,String expected)
    {
        TestManager.setTestStep(description, expected);
    }

    private static void report(String obj,String type)
    {
        String actual ="";
        boolean objectExist = FindElement(obj,type).isDisplayed();
        if(objectExist)
        {
            actual=TestManager.testStep.Expected+" performed successfully";
        }
        else
            actual = obj + "is not found on the BrowserName";

        TestManager.setStepResults(actual, ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64), objectExist);
    }


    public void DriverInit()
    {
        logger.info("Executing using " +EnvironmentManager.Browser+" Driver");
        switch(EnvironmentManager.Browser)
        {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", EnvironmentManager.driverPath+"chromedriver.exe");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--no-sandbox");
                options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type", "--ignore-certificate-errors"));
                driver = new ChromeDriver(options);
            break;

            case "IE":
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setCapability("ignoreZoomSetting", true);
                System.setProperty("webdriver.ie.driver", EnvironmentManager.driverPath+"IEDriverServer.exe");
                driver = new InternetExplorerDriver(caps);
                break;
            case "FireFox":
                System.setProperty("webdriver.gecko.driver", EnvironmentManager.driverPath+"geckodriver.exe");
                driver = new FirefoxDriver();
            break;

            case "Opera":
                System.setProperty("webdriver.opera.driver", EnvironmentManager.driverPath+"OperaSetup.exe");
                driver =new OperaDriver();
                break;

        }


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


    public static void Click(String obj,String type)
    {
        logger.info("Performing click on object"+obj+" using "+type);
        report(obj,type);
        FindElement(obj,type).click();
    }

    public static void Type(String obj,String type,String val)
    {
        logger.info("Performing Type action on object"+obj+" using "+type+" with value :"+val);
        report(obj,type);
        FindElement(obj,type).sendKeys(val);
    }

    public static void ClearAndType(String obj,String type,String val)
    {
        logger.info("Performing Clear and Type action on object"+obj+" using "+type+" with value :"+val);
        report(obj,type);
        FindElement(obj,type).clear();
        FindElement(obj,type).sendKeys(val);
    }


    public static void ScrollDown()
    {
        logger.info("Perform scroll down");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    public static void ScrollToElement(String obj)
    {
        try
        {
            logger.info("Perform scroll to element");
            WebElement element = driver.findElement(By.xpath(map.get(obj)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }
        catch (Exception e)
        {
            logger.info("An Error occurred scrolling the page");
        }

    }


    public static void ScrollUp()
    {

    }

    public static void TextIs(String obj,String type,String text)
    {
        logger.info("Checking if provided text match what is displayed on the BrowserName :");
        String val = FindElement(obj,type).getText();
        String actual ="";
        boolean sameText;
        if(text.equals(val))
        {
            sameText=true;
            actual ="Text displayed as Expected";
        }
        else
        {
            sameText=false;
            actual="Text displayed incorrectly";
        }
        logger.info("Expected :" +text+ " Actual :"+val);
        TestManager.setStepResults(actual, ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64),sameText);
    }

    public static String getText(String obj)
    {
        return null;
    }

    public static void CloseTestCaseExecution()
    {
        driver.quit();
    }

    private static WebElement FindElement(String obj,String type)
    {
        logger.info("Searching for element "+obj);
        try
        {
            switch (type)
            {
                case "XPATH":
                    return driver.findElement(By.xpath(map.get(obj)));
                case "ID":
                    return driver.findElement(By.id(map.get(obj)));
                case "NAME":
                    return driver.findElement(By.name(map.get(obj)));
                case "CLASS":
                    return driver.findElement(By.className(map.get(obj)));
                case "LINK":
                    return driver.findElement(By.linkText(map.get(obj)));
                default:
                    return driver.findElement(By.xpath(map.get(obj)));
            }
        }
        catch(Exception e)
        {
            logger.info("An error ocured searching for an element "+obj);
            return null;
        }

    }



}
