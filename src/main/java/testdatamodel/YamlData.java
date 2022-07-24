package testdatamodel;

import com.fasterxml.jackson.databind.JsonNode;

public class YamlData {
	private String testCaseName;
	private JsonNode testData;

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public JsonNode getTestData() {
		return testData;
	}

	@Override
	public String toString() {
		return "JsonData [testCaseName=" + testCaseName + ", testData=" + testData + "]";
	}

	public void setTestData(JsonNode testData) {
		this.testData = testData;
	}
	public String getValue(String fieldName) {
		return testData.get(fieldName).asText();
	}

}
