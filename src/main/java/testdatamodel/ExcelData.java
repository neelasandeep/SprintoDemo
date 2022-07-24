package testdatamodel;

import java.util.HashMap;
import java.util.Map;

public class ExcelData {
	private String testCaseName;
	private Map<String, Object> testDataSet;
	private String testCaseID;
	private String testCaseType;

	public String getTestCaseID() {
		return testCaseID;
	}

	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}

	public String getTestCaseType() {
		return testCaseType;
	}

	public void setTestCaseType(String testCaseType) {
		this.testCaseType = testCaseType;
	}

	public ExcelData(String testCaseName) {
		this.testCaseName = testCaseName;
		testDataSet = new HashMap<>();
	}

	@Override
	public String toString() {
		return "ExcelData [testCaseName=" + testCaseName + ", testDataSet=" + testDataSet + ", testCaseID=" + testCaseID
				+ ", testCaseType=" + testCaseType + "]";
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	

	public Map<String, Object> getTestDataSet() {
		return testDataSet;
	}

	public void setTestData(String key, Object value) {
		testDataSet.put(key, value);
	}

	public String getTestDataAsString(String key) {
		return String.valueOf(testDataSet.get(key));
	}

}
