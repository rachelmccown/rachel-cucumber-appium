import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
            case("Out of Milk"):
                capabilities.setCapability("appPackage", "com.capigami.outofmilk");
                capabilities.setCapability("appActivity", "com.capigami.outofmilk.MainActivity");
                break;
        }
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
    }

    @And("^I (.*) (-?\\d+) and (-?\\d+)$")
    public void calculate(String function, int input1, int input2){

        function = function.toLowerCase();
        switch(function){
            case("add"):
                driver.findElement(By.id("com.android.calculator2:id/formula")).sendKeys(Integer.toString(input1));
                driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
                driver.findElement(By.id("com.android.calculator2:id/formula")).sendKeys(Integer.toString(input2));
                break;
        }
        driver.findElement(By.id("com.android.calculator2:id/eq")).click();
        output = (MobileElement) driver.findElement(By.id("com.android.calculator2:id/result"));
    }

    @And("^I go to the (.*)$")
    public void goTo(String page){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.capigami.outofmilk:id/action_skip")));
        driver.findElement(By.id("com.capigami.outofmilk:id/action_skip")).click();
    }

    @Then("^the result is (.*)$")
    public void getResult(String result){

        String expected = result;
        String actual = output.getText();
        if(output!= null) {
            Assert.assertEquals(expected, actual);
        }
    }

    @Then("^the page displays (.*)$")
    public void pageDisplay(String obj){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.capigami.outofmilk:id/shopping_list_instructions")));
        WebElement instructions = driver.findElement(By.id("com.android.calculator2:id/shopping_list_instructions "));
        Assert.assertTrue(instructions.isDisplayed());
    }

}
