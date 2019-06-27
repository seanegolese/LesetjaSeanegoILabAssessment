package Framework;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Utils {


   public static Logger logger = Logger.getLogger("MyLog");
    static FileHandler fh;

    public void writeToFile(String a)
    {
        try{
            FileWriter fw=new FileWriter("Test.txt");
            fw.write(a);
            fw.close();
        }catch(Exception e){System.out.println(e);}
        System.out.println("Success...");
    }

    public static void LogInit()
    {
        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("./src/main/Files/Logging/Log.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public static String getRandomPhoneNumber()
    {
        int n=7; //7 values
        int m = (int) Math.pow(10, n - 1);
        return "083"+Integer.toString(m + new Random().nextInt(9 * m));
    }



    public String readFile()
    {
        return "";
    }




    public static void copyFolder(String source,String destination)
    {
        File srcDir = new File(source);

        File destDir = new File(destination);

        try {
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String getCurrentTime()
    {
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        return ""+ts;
    }


}
