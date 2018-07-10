import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppStepDefinitions {

    AndroidDriver<MobileElement> driver;
    DesiredCapabilities capabilities;
    MobileElement output;

    @Before
    public void setup(){
        capabilities = DesiredCapabilities.android();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setBrowserName("Chrome");

        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("deviceName", "Nexus 5X API 25");
//        capabilities.setCapability("platformVersion", "7.1.1");
        capabilities.setCapability("deviceName", "Nexus 5X API 27");
        capabilities.setCapability("platformVersion", "8.1");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hd80s\\node_modules\\appium-chromedriver\\chromedriver\\win\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
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
            case("Chrome"):
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                capabilities.setBrowserName("Chrome");
                capabilities.setCapability("appPackage", "com.android.chrome");
                capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        }
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @And("^I go to the (.*)$")
    public void goTo(String page){

        WebDriverWait wait = new WebDriverWait(driver, 30);
        switch(page){
            case("Main Page"):
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.capigami.outofmilk:id/action_skip")));
                driver.findElement(By.id("com.capigami.outofmilk:id/action_skip")).click();
                break;
            case("Nav Bar"):
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                MobileElement navigation_drawer = driver.findElementByAccessibilityId("Open navigation drawer");
                navigation_drawer.click();
                break;
            case("Recipe Book"):
                MobileElement recipeBook = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout" +
                        "/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
                        "FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget." +
                        "RecyclerView/android.support.v7.widget.LinearLayoutCompat[4]/android.widget.CheckedTextView")));
                recipeBook.click();
                break;
            case("Options"):
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                driver.findElementByAccessibilityId("More options").click();
        }
    }

    @And("^I add (.*) to the list$")
    public void addItemToList(String item){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String [] addItems = item.split(",");
        WebElement searchBar = driver.findElement(By.id("com.capigami.outofmilk:id/inputFieldAutoCompleteView"));
        for (String s : addItems) {
            searchBar.sendKeys(s);
            driver.findElement(By.id("com.capigami.outofmilk:id/submit")).click();
        }
    }

    @And("^I find a recipe for (.*)$")
    public void createRecipe(String dish){
        driver.findElement(By.id("com.capigami.outofmilk:id/browse_epicurious")).click();
        driver.findElement(By.xpath("//android.webkit.WebView[@content-desc=\"Epicurious " +
                "– Recipes, Menu Ideas, Videos & Cooking Tips\"]/android.view.View/android.view" +
                ".View[2]/android.view.View[2]")).sendKeys(dish);
    }

    @And("^in categories I add (.*)$")
    public void addCategory(String cat){
        driver.findElement(By.xpath("\t/hierarchy/android.widget.FrameLayout/android.widget." +
                "FrameLayout/android.widget.ListView/android.widget.LinearLayout[7]")).click();
        driver.findElement(By.id("com.capigami.outofmilk:id/action_create_category")).click();
        driver.findElement(By.id("com.capigami.outofmilk:id/custom")).sendKeys(cat);
        driver.findElement(By.id("android:id/button1")).click();
    }

    @And("^I search for (.*)$")
    public void search(String term){

        WebDriverWait wait = new WebDriverWait(driver, 30);
//        MobileElement element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/terms_accept")));
//        element.click();
//        element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/negative_button")));
//        element.click();
//        MobileElement element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/search_box_text")));
//        MobileElement element = driver.findElement(By.id("com.android.chrome:id/search_box_text"));
//        element.click();
        MobileElement element = driver.findElement(By.id("com.android.chrome:id/url_bar"));
        element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/url_bar")));
        element.click();
        element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.chrome:id/ntp_content")));
        element.sendKeys(term);
        element.sendKeys(Keys.RETURN);
    }

    @Then("^the page displays (.*)$")
    public void pageDisplay(String obj){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.capigami.outofmilk:id/shopping_list_instructions")));
        WebElement instructions = driver.findElement(By.id("com.capigami.outofmilk:id/shopping_list_instructions"));
        Assert.assertTrue(instructions.isDisplayed());
    }

    @Then("^there is (\\d+) items? on the list$")
    public void isItemOnList(String itemCount){
        WebElement list = driver.findElement(By.id("com.capigami.outofmilk:id/num_items_list"));
        String expected = "Items in list: " + itemCount;
        String actual = list.getText();
        Assert.assertEquals(expected, actual);
    }

    @Then("^(.*) is a category$")
    public void checkList(String cat){
        boolean exists = false;
        List<MobileElement> list = driver.findElementsByClassName("android.widget.TextView");
        for (MobileElement m: list) {
            if(m.getText().equals(cat)){
                exists = true;
            }
        }
        Assert.assertTrue(exists);
    }

    @And("^I (.*) the numbers (-?\\d+) and (-?\\d+)$")
    public void calculate(String function, int input1, int input2){

        function = function.toLowerCase();
        String formula;
        switch(function){
            case("add"):
                formula = Integer.toString(input1) + "+" + Integer.toString(input2);
                driver.findElement(By.id("com.android.calculator2:id/formula")).sendKeys(formula);
                break;
            case("subtract"):
                formula = Integer.toString(input1) + "-(" + Integer.toString(input2) + ")";
                driver.findElement(By.id("com.android.calculator2:id/formula")).sendKeys(formula);
                break;
        }
        driver.findElement(By.id("com.android.calculator2:id/eq")).click();
        output = driver.findElement(By.id("com.android.calculator2:id/result"));
    }

    @Then("^the result is (.*)$")
    public void getResult(String result){

        String expected = result;
        String actual = output.getText();
        if(output != null) {
            Assert.assertEquals(expected, actual);
        }
    }

}
