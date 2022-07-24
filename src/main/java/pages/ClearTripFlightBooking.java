package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testdatamodel.YamlData;
import uiActions.GenericActions;
@Slf4j
public class ClearTripFlightBooking {

WebDriver driver;
GenericActions genActions;
YamlData testData;
    public ClearTripFlightBooking(WebDriver driver, YamlData testData) {

        this.driver = driver;
        this.testData=testData;
        genActions=new GenericActions(driver);
        PageFactory.initElements(this.driver, this);
    }
    @FindBy(xpath = "((//div[@class='p-relative'])/div/input)[1]")
    private WebElement from;
    @FindBy(xpath = "//*[local-name()='svg' and @class=' c-pointer c-neutral-900']")
    private WebElement closeLoginAd;
    @FindBy(xpath = "((//div[@class='p-relative'])/div/input)[2]")
    private WebElement to;
    @FindBy(xpath = "//p[@class='to-ellipsis o-hidden ws-nowrap c-inherit fs-3']")
    private WebElement selectCity;
    @FindBy(xpath = "//*[local-name()='svg' and @class='t-all ml-2']")
    private WebElement datePicker;

    @FindBy(xpath = "//div[@aria-selected='true']")
    private WebElement currentDate;
    @FindBy(xpath = "//button[text()='Search flights']")
    private WebElement searchBtn;

    @FindBy(xpath = "(//button[text()='Book'])[1]")
    private WebElement bookFlight;

    @FindBy(xpath = "//button[text()='Continue']")
    private WebElement continueBooking;
    @FindBy(xpath = "//button[text()='Select']")
    private WebElement selectPrice;

    @FindBy(xpath = "//input[@data-testid='firstName']")
    private WebElement PassengerFirstName;
    @FindBy(xpath = " //input[@id='last-name 0']")
    private WebElement PassengerLastName;

    @FindBy(xpath = "(//h2)[1]")
    private WebElement msg;












    public boolean selectFlights() {
        if (genActions.isElementPresent(closeLoginAd))
            closeLoginAd.click();
        genActions.waitForElementAndUpdateText(from,testData.getValue("from"));
        genActions.waitForElementAndThenClick(selectCity);
        genActions.waitForBrowser(2);
        genActions.waitForElementAndUpdateText(to,testData.getValue("to"));
        genActions.waitForBrowser(2);
        genActions.waitForElementAndThenClick(selectCity);
        genActions.waitForBrowser(3);
        selectDate();
        genActions.waitForElementAndThenClick(searchBtn);


        return selectLowestPriceFlightInClearTrip();

    }

    public void selectDate(){
        genActions.waitForElementAndThenClick(datePicker);
        genActions.waitForBrowser(2);
        int dayCount=(Integer.parseInt(currentDate.getText())+7);
        log.info(String.valueOf(dayCount));
        String depDayValue =dayCount>31?String.valueOf(dayCount-31):String.valueOf(dayCount);

        String xpathForDepartureDate="//div[@class='DayPicker-Month']//div[@aria-disabled='false']//div[text()='" +depDayValue+"']";
        WebElement departureDate = driver.findElement(By.xpath(xpathForDepartureDate));
        genActions.waitForElementAndThenClick(departureDate);
        genActions.waitForBrowser(2);
    }

    public boolean selectLowestPriceFlightInClearTrip(){
        genActions.waitForElementAndThenClick(bookFlight);
        String currentWindow = genActions.swithToNewTabCloseTheCurrentTab();
        if (genActions.isElementPresent(closeLoginAd))
            closeLoginAd.click();
        genActions.scrollToElement(driver,continueBooking);
        genActions.waitForBrowser(2);
       genActions.waitForElementAndThenClick(continueBooking);
       genActions.waitForElementAndThenClick(selectPrice);
       genActions.waitForElementAndUpdateText(PassengerFirstName,"Smith");
       genActions.waitForElementAndUpdateText(PassengerLastName,"William");
       boolean result= msg.getText().equals("Add traveller details");
        driver.close();
        driver.switchTo().window(currentWindow);
        return result;

    }

}
