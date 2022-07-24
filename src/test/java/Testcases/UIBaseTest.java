package Testcases;

import browserfactory.BrowserFactory;
import constants.ApplicationConstants;
import constants.Dataconstants;
import customexceptions.FrameWorkException;
import customexceptions.TestDataException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import testdatamanagement.TestDataResource;
import utilities.PropertiesUtility;


import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public abstract class UIBaseTest {

    BrowserFactory browserFactory = new BrowserFactory();
     WebDriver driver;
    public ConcurrentHashMap<Integer,WebDriver> drivers= new ConcurrentHashMap<>();



    @BeforeSuite
    public void setup() {

        String browser = PropertiesUtility.getProperty(ApplicationConstants.BROWSER);


        driver=browserFactory.createInstance(browser);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(getEnvUrl("APPURL"));



    }
    public String getEnvUrl(String environment) {
        String url = null;
        try {
            if(environment.equals("DEV01")) {
                url= PropertiesUtility.getProperty(ApplicationConstants.DEV01);
            }else if(environment.equals("QA02")) {
                url= PropertiesUtility.getProperty(ApplicationConstants.QA02);
            }else if(environment.equals("APPURL")){
                url= PropertiesUtility.getProperty(ApplicationConstants.APPURL);
            }
        }catch(NullPointerException e) {
            log.info("no URL present in the properties file");
        }

        return url;
    }
    public abstract String getTestDataParser();
    @DataProvider(name = "testdata")
    public Object[][] testdata(Method testMethod) throws TestDataException {
        if (getTestDataParser().equals(Dataconstants.JSON) || getTestDataParser().equals(Dataconstants.YAML)
                || getTestDataParser().equals(Dataconstants.XLSX)) {

            return TestDataResource.getdata(testMethod.getName());

        } else {
            throw new TestDataException("invalid test data file");
        }

    }
    @AfterSuite
    public void destroyBrowser(){
        driver.close();
        driver.quit();
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({ "testdata", "sheetname" })
    public void loadData(@Optional String inputdatafile, @Optional String sheetname) throws FrameWorkException {
        if (inputdatafile == null) {
            throw new FrameWorkException("testdata filename is null");
        } else {

            loadtestdata(inputdatafile, sheetname);
        }
    }

    public void loadtestdata(String inputdatafile, String sheetName) {
        TestDataResource.setTestData(inputdatafile, sheetName);
    }







}
