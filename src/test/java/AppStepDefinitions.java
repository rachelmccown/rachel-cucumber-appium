import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppStepDefinitions {

    AndroidDriver driver;
    DesiredCapabilities capabilities;
    MobileElement output;

    @Before
    public void setup(){

        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus 5X API 25");
        capabilities.setCapability("platformVersion", "7.1.1");

    }

    @When("^I open the (.*) app$")
    public void calculatorTester(String application) throws MalformedURLException {

        switch(application){
            case("Calculator"):
                capabilities.setCapability("appPackage", "com.android.calculator2");
                capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
                break;
        }

        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
    }

    @And("^I (.*) (\\d+) and (\\d+)$")
    public void calculate(String function, int input1, int input2){

        function = function.toLowerCase();

        switch(function){
            case("add"): driver.findElement(By.id("com.android.calculator2:id/digit_" + String.valueOf(input1))).click();
                driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
                driver.findElement(By.id("com.android.calculator2:id/digit_" + String.valueOf(input2))).click();
                break;
        }

        driver.findElement(By.id("com.android.calculator2:id/eq")).click();
        output = (MobileElement) driver.findElement(By.id("com.android.calculator2:id/result"));

    }

    @Then("^the result is (\\d+)$")
    public void getResult(int result){

        int out = Integer.parseInt(output.getText());

        if(output!= null) {
            Assert.assertEquals(result, out);
        }
    }

}
