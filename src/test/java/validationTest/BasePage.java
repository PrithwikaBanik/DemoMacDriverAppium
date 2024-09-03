package validationTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.mac.Mac2Driver;
import io.appium.java_client.mac.options.Mac2Options;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;


public class BasePage {

    public static AppiumDriver driver;


    public static void initDriver() throws MalformedURLException {

        if (driver == null) {
            /*DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "mac");
            capabilities.setCapability("platformVersion", "10.14.2");
            capabilities.setCapability("deviceName", "Mac");
            capabilities.setCapability("automationName", "mac2");
            capabilities.setCapability("app", "/Applications/Postman.app");
           // capabilities.setCapability("bundleId", "com.futuremind.postman"); // Postman app bundle ID
            capabilities.setCapability("showServerLogs", true);
            Mac2Options options = new Mac2Options();
            options.merge(capabilities);
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723"), options);
*/

            /*Mac2Options options = new Mac2Options();
            options.setCapability("platformName", "Mac");
            options.setCapability("platformVersion", "10.14.2");
            options.setCapability("deviceName", "Mac");
            options.setCapability("automationName", "mac2");
            options.setCapability("app", "/Applications/Postman.app");
            options.setCapability("bundleid", "com.postmanlabs.mac%");
            options.setCapability("newCommandTimeout", 300); // Increase timeout to 300 seconds
            */

            Mac2Options options = new Mac2Options();
            options.setPlatformName("mac");
            options.setAutomationName("mac2");
            options.setNewCommandTimeout(Duration.ofSeconds(300)); // Increase timeout to 300 seconds
            options.setBundleId("com.postmanlabs.mac");

            driver = new AppiumDriver(new URL("http://127.0.0.1:4723"), options);
            //driver = new Mac2Driver(options);
        }
    }


    @BeforeStep
    public void beforeStep() throws MalformedURLException {
        initDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("  @AfterStep");
        //driver.quit();
        //driver = null;
        //final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);

        //scenario.embed(screenshot, "image/jpeg"); // stick it in the report
    }



        @Given("User opens the application")
        public void userOpensTheApplication ()  {
            //driver.("com.ypsomed.controlapp");
            try {
                System.out.println("Launching Postman app using AppleScript");
                //Runtime.getRuntime().exec("open -a Postman");
                //driver.get("com.postmanlabs.mac");
                System.out.println("Postman app launched");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Then("checks the header title text")
        public void checksTheHeaderTitle () {


            // Find "File" from Menu
             WebElement element = findElementByNameWithin("File", 15);
             if (element != null)
                clickElement(element);

            // Find "New..." from Menu
            element = driver.findElement(By.xpath("//XCUIElementTypePopUpButton[@value='New...']"));
            if (element != null)
                clickElement(element);

            //driver.findElement(By.name("Home")).click();

            //Assert.assertEquals(headerText,"Appium validation");
            //System.out.println("Header Text is expected text");
        }

        private WebElement findElementByNameWithin(String elementName, int waitUntil) {

            // Wait for the element to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitUntil));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementName)));

            // WebElement element = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Home']"));
            if (element.isDisplayed()) {
                System.out.println("Element is displayed");
                return element;
                //element.click();
            }

            return null;
        }


    private void clickElement(WebElement elementToClick) {
        elementToClick.click();
    }


        @Then("writes some text to the text area")
        public void writesSomeTextToTheTextArea () {


           System.out.println("Expected Text is sent to the text field");
        }

        @Then("clicks on setup button")
        public void clicksOnSetupButton()  {
        }

        @Then("switches the toggle")
        public void switchTheToggle () {

        }

        @Then("quits the application")
        public void quitsTheApplication() {

            //driver.terminateApp("com.ypsomed.controlapp");
            //driver.quit();
            try {
                // Terminate Postman application
                Runtime.getRuntime().exec("pkill -f Postman");
                System.out.println("Postman application terminated");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    @Then("writes {string} to the text area")
    public void writesSampleTextToTheTextArea(String sampleText) {

    }


    @When("^on the (.*) page snooze for (.*) seconds$")
    public void snooze(String page, String duration) throws Throwable {

        int timeoutMax = Integer.valueOf(duration);

        boolean isTimeout = false;
        int timeoutCount = 0;
        while (!isTimeout) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            timeoutCount++;
            if (timeoutCount > timeoutMax) {
                isTimeout = true;
            }
        }
    }
}




