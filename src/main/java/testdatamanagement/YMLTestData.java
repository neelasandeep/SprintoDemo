package testdatamanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import constants.ApplicationConstants;
import constants.Dataconstants;
import lombok.extern.slf4j.Slf4j;

import testdatamodel.YamlData;
import utilities.FilePathBuilder;
import utilities.PropertiesUtility;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class YMLTestData implements TestData {
    private static YamlData[] testdata;

    static YamlData data = null;

    public void setTestData(String inputDataFile) {
        ObjectMapper mapper;
        FilePathBuilder filePathBuilder = new FilePathBuilder(inputDataFile);
        filePathBuilder.setParentDirectory(Dataconstants.TESTDATA_DIRECTORY);
        try {
            mapper = new ObjectMapper(new YAMLFactory());
            testdata = mapper.readValue(new File(filePathBuilder.getFilePath()), YamlData[].class);
        } catch (IOException e) {

            log.info(String.valueOf(e));
        }
    }

    public synchronized Object[][] getdata(String testCaseName) {

        Arrays.asList(testdata).forEach(jsondata -> {

            if (jsondata.getTestCaseName().equals(testCaseName)) {
                data = jsondata;
            }
        });
        return  new Object[][]{{data}};

    }

}
