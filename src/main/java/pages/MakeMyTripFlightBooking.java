package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testdatamodel.YamlData;
import uiActions.GenericActions;



@Slf4j
public class MakeMyTripFlightBooking {
    GenericActions genActions;



    /*======================================
    *
    * makemytrip xpaths
    *
    * */
    @FindBy(xpath = "//input[@id='fromCity']")
    private WebElement from;
    @FindBy(xpath = "//input[@id='toCity']")
    private WebElement to;
    @FindBy(xpath = "//input[@placeholder='From']")
    private WebElement fromInput;
    @FindBy(xpath = "//input[@placeholder='To']")
    private WebElement toInput;
    @FindBy(xpath = "//label[@for='departure']")
    private WebElement departure;
    @FindBy(xpath = "(//div[@class='DayPicker-Day DayPicker-Day--selected']//p)[1]")
    private WebElement currentDay;
    @FindBy(xpath = "//a[text()='Search']")
    private WebElement searchBtn;
    @FindBy(xpath = "//span[@class='bgProperties icon20 overlayCrossIcon']")
    private WebElement closeSuggestion;
    @FindBy(xpath = "((//div[@id='premEcon']//div[@class='makeFlex simpleow'])[1]//p)[contains(text(),'₹')]")
    private WebElement lowestPriceFlight;

    @FindBy(xpath = "//button[@class='button actionBtn ']")
    private WebElement allLowestFlightsList;

    @FindBy(xpath = "(//button//span[text()='View Prices'])[1]")
    private WebElement flightSelection;

    @FindBy(xpath = "(//button[text()='Book Now'])[1]")
    private WebElement bookSelectedFlight;

    @FindBy(xpath = "//div[@class='pageHeader']/h2")
    private WebElement bookingPage;

    WebDriver driver;
    YamlData testData;
    public MakeMyTripFlightBooking(WebDriver driver, YamlData testData) {

        this.driver = driver;
        this.testData=testData;
        genActions=new GenericActions(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean selectFlight(){

        genActions.waitForElementAndThenClick(from);
        genActions.waitForElementAndUpdateText(fromInput,testData.getValue("from"));
        genActions.waitForBrowser(2);
        fromInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        genActions.waitForElementAndThenClick(to);
        genActions.waitForElementAndUpdateText(toInput,testData.getValue("to"));
        genActions.waitForBrowser(2);
        toInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        genActions.waitForBrowser(1);
        selectDate();
        genActions.waitForElementAndThenClick(searchBtn);
        genActions.waitForBrowser(5);
        if(genActions.isElementPresent(closeSuggestion))
            genActions.waitForElementAndThenClick(closeSuggestion);

        selectLowestPriceFlight();
        genActions.waitForBrowser(3);
        return checkBookingPage();

    }


    public void selectDate(){
        int dayCount=(Integer.parseInt(currentDay.getText())+7);
        String depDayValue =dayCount>31?String.valueOf(dayCount-31):String.valueOf(dayCount);
        String xpathForDepartureDate=" (//div[@class='DayPicker-Month']//div[@class='DayPicker-Day']//p[(text()='" +depDayValue+"')])";
        WebElement departureDate = driver.findElement(By.xpath(xpathForDepartureDate));
        genActions.waitForElementAndThenClick(departureDate);

    }
    public void selectLowestPriceFlight(){
        genActions.waitForElementAndThenClick(allLowestFlightsList);
        genActions.waitForElementAndThenClick(flightSelection);
        genActions.waitForElementAndThenClick(bookSelectedFlight);
    }
    public boolean checkBookingPage(){

        String currentWindow = genActions.swithToNewTabCloseTheCurrentTab();
        log.info(bookingPage.getText());
        boolean result= bookingPage.getText().equals("Complete your booking");
        driver.close();
        driver.switchTo().window(currentWindow);
       return result;
    }





}
