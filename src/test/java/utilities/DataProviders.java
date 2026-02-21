package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {

    	String path = System.getProperty("user.dir")
    	        + "/src/test/resources/testData/opencart.xlsx";
        ExcelUtilities xlutility = new ExcelUtilities(path);
        String sheetName = "Sheet1";

        int totalRows = xlutility.getRowCount(sheetName);
        int totalCols = xlutility.getCellCount(sheetName, 0); // header row

        if (totalRows <= 1) {
            return new Object[0][0];
        }

        Object[][] loginData = new Object[totalRows - 1][totalCols];

        for (int i = 1; i < totalRows; i++) { // skip header
            for (int j = 0; j < totalCols; j++) {
                loginData[i - 1][j] = xlutility.getCellData(sheetName, i, j);
            }
        }

        return loginData;
    }
}
