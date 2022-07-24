package testdatamanagement;

import constants.Dataconstants;
import customexceptions.TestDataException;

public class TestDataResource {
    static TestData testData;

    private TestDataResource() {
    }

    public static void setTestData(String inputFileName, String sheetName) {

        testData = new YMLTestData();
        testData.setTestData(inputFileName);
    }

    public static Object[][] getdata(String testCaseName) throws TestDataException {
        return testData.getdata(testCaseName);
    }


}
