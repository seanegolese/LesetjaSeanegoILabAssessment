package Framework;

import java.io.File;
import java.util.Date;

import static Framework.Utils.copyFolder;

public class EnvironmentManager {
    public static String Environment ="Production";
    public static String ProjectFolder;
    public static String SuiteName="ILAB Assessment";
    public static String url ="https://www.ilabquality.com/";
    public static String driverPath="./src/main/Files/WebDriver/";
    public static String reportName;
    public static String Browser ="Chrome";

    public static void createReportDir()
    {
        Date date= new Date();
        reportName="Report "+date.getTime();

        File file = new File("./src/test/Reports/"+reportName);
        if (!file.exists()) if (file.mkdir()) {
            copyFolder("./src/main/Files/RepTemp","./src/test/Reports/"+reportName);
        }
    }
}
