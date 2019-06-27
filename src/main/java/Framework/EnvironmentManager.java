package Framework;

import java.io.File;
import java.util.Date;

import static Framework.Utils.copyFolder;

public class EnvironmentManager {
    public static String Environment;
    public static String SuiteName;
    public static String url;
    public static String driverPath;
    public static String reportName;
    public static String Browser;
    public static String dbConnString;
    public static String username;
    public static String passwords;



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
