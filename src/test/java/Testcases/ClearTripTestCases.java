package Testcases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ClearTripFlightBooking;
import testdatamodel.YamlData;


public class ClearTripTestCases extends UIBaseTest{
    @Override
    public String getTestDataParser() {
        return "yml";
    }


    @Test(dataProvider="testdata",priority=2)
    public void clearTripFlightBooking(YamlData data){
        ClearTripFlightBooking cb= new ClearTripFlightBooking(driver,data);
        Assert.assertTrue(cb.selectFlights());

    }

}
