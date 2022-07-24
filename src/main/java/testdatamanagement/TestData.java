package testdatamanagement;

import customexceptions.TestDataException;

public interface TestData {
	public void setTestData(String inputDataFile);

	public Object[][] getdata(String testCaseName) throws TestDataException;

}
