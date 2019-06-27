package Framework;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.testng.annotations.DataProvider;



public class DataManager {

    protected Connection connection;

    public static Object[][] excelDataProviderMethod(String folder) {

        Object[][] retObjArr = { { folder } };
        try {
            File path = new File("");
            FileInputStream file = new FileInputStream(
                    new File( "./src/main/Files/TestData/TestData.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Details");

            int rowCount = sheet.getPhysicalNumberOfRows() - 1;
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            retObjArr = new Object[rowCount][colCount];
            for (int rownum = 0; rownum < rowCount; rownum++) {
                Row row = sheet.getRow(rownum + 1);

                for (int col = 0; col < colCount; col++) {
                    retObjArr[rownum][col] = row.getCell(col).getStringCellValue();
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return retObjArr;
    }



    public void connectToMySqlDatabase()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to Database...");
            connection = DriverManager.getConnection(EnvironmentManager.dbConnString, EnvironmentManager.username, EnvironmentManager.passwords);
            if (connection != null) {
                System.out.println("Connected to the Database...");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }



    @DataProvider(name = "Details")
    public static Object[][] ApplicantDetails() {
        return excelDataProviderMethod("./src/main/Files/TestData/");
    }
}
