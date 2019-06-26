package Framework;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class Utils {
    public void writeToFile(String a)
    {
        try{
            FileWriter fw=new FileWriter("C:\\Users\\f5035929\\Documents\\Seanego IT\\ILAB\\Lesetja Seanego ILAB Automation Assessment\\Framework\\Test.txt");
            fw.write(a);
            fw.close();
        }catch(Exception e){System.out.println(e);}
        System.out.println("Success...");
    }

    public static String getRandomPhoneNumber()
    {
        int n=7;
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
