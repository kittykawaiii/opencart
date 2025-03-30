package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    //Data Provider 1
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        String path = "/Users/dieulypham/IdeaProjects/OpenCart/testData/OpenCart_LoginData.xlsx";
        ExcelUtility xlUtil = new ExcelUtility(path); //creating an object for XLUtility

        int totalRows = xlUtil.getRowCount("Sheet1");
        int totalCols = xlUtil.getCellCount("Sheet1", 1);

        String[][] loginData = new String[totalRows][totalCols]; //Created for 2 dimension array
        for (int i = 1; i <= totalRows; i++) { //1 for reading the data from xl storing in 2 dimension array
            for (int j = 0; j < totalCols; j++) { //0 i is rows j is col
                loginData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);
            }
        }
        return loginData; //returning 2 dimension array
    }
    //Data Provider 2

    //Data Provider 3

    //Data Provider 4





    }

