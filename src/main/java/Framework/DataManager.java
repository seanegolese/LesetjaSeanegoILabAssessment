package Framework;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class DataManager {
    public static Object[][] excelDataProviderMethod(String folder) {

        Object[][] retObjArr = { { folder } };
        try {
            File path = new File("");
            FileInputStream file = new FileInputStream(
                    new File( "C:\\Users\\f5035929\\Documents\\Assessment\\src\\main\\Files\\TestData\\TestData.xlsx"));
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
            System.out.println("Done" + colCount);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return retObjArr;
    }




    @DataProvider(name = "Details")
    public static Object[][] ApplicantDetails() {
        return excelDataProviderMethod("C:\\Users\\f5035929\\Documents\\Assessment\\src\\main\\Files\\TestData\\");
    }
}
