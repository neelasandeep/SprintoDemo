package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MakeMyTripFlightBooking;
import testdatamodel.YamlData;

public class MakeMyTripTestCases extends UIBaseTest{
    @Override
    public String getTestDataParser() {
        return "yml";
    }


    @Test(dataProvider = "testdata", priority=1)
    public void makeMyTripFlightBook(YamlData data){
        MakeMyTripFlightBooking fb= new MakeMyTripFlightBooking(driver,data);
        Assert.assertTrue( fb.selectFlight());
    }

}
