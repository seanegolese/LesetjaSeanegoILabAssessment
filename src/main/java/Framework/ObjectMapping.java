package Framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class ObjectMapping {
    public static Map<String, String> map = new HashMap<String, String>();

    public void loadObjectFile()
    {
        String[] pairs = mappedObjects().split(";");

        for (String string : pairs) {
            String[] keyValue = string.split("~");
            map.put(keyValue[0], keyValue[1]);
        }
      //  return map;
    }

    private String mappedObjects()
    {
        try
        {
            File file = new File("./src/main/Files/PageObjectModel/ObjFile.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));

            StringBuilder sb = new StringBuilder();

            br.readLine();
            String st;
            while ((st=br.readLine()) != null)
                sb.append(st);
            return sb.toString();

        }
        catch (Exception e)
        {
            return "File not found";
        }

    }


    public static void LoadMap()
    {
        try
        {
            File inputFile = new File("./src/main/Files/PageObjectModel/ILabWebSite.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);




        }
        catch (Exception e)
        {

        }

    }


}
