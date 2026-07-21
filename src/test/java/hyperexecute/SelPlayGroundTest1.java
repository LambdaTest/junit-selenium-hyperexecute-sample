package hyperexecute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class SelPlayGroundTest1 {

    protected RemoteWebDriver driver = null;
    public static String status;

    String gridURL = "@hub.lambdatest.com/wd/hub";
    String user_name = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" : System.getenv("LT_USERNAME");
    String access_key = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");

    @BeforeAll
    public static void start() {
        System.out.println("Running JUnit test on HyperExecute Grid");
    }

    @BeforeEach
    public void setup() {
        System.out.println("Setting up resources to run tests on HyperExecute Grid");
    }

    public void SetUpBrowser(String browserName, String version, String platform, String build, String name) {

        MutableCapabilities capabilities = new MutableCapabilities();

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", version);
        capabilities.setCapability("platformName", platform);

        MutableCapabilities ltOptions = new MutableCapabilities();
        ltOptions.setCapability("build", build);
        ltOptions.setCapability("name", name);
        ltOptions.setCapability("network", true);
        ltOptions.setCapability("visual", true);
        ltOptions.setCapability("video", true);
        ltOptions.setCapability("console", true);
        ltOptions.setCapability("selenium_version", "4.24.0");

        capabilities.setCapability("LT:Options", ltOptions);

        try {
            driver = new RemoteWebDriver(
                    new URL("https://" + user_name + ":" + access_key + gridURL),
                    capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("setup_testEnvironment")
    public void test_SimpleFormDemo(String browserName,
                                    String version,
                                    String platform,
                                    String build,
                                    String name) {

        SetUpBrowser(browserName, version, platform, build, name);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {

            driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");

            String message = "Hello HyperExecute";

            driver.findElement(By.id("user-message")).clear();
            driver.findElement(By.id("user-message")).sendKeys(message);

            driver.findElement(By.id("showInput")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement output = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("message")));

            assertEquals(message, output.getText());

            status = "passed";

        } catch (Exception e) {
            status = "failed";
            throw e;
        }
    }

    @AfterEach
    public void TearDownClass() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
            System.out.println("Browser resources released");
        }
    }

    @AfterAll
    public static void endTest() {
        System.out.println("JUnit execution on HyperExecute Grid complete");
    }

    static Stream<Arguments> setup_testEnvironment() {

        String platform_name = System.getenv("TARGET_OS");

        return Stream.of(
                arguments(
                        "Chrome",
                        "latest",
                        platform_name,
                        "[Test-1] JUnit Selenium Playground",
                        "[Test-1] JUnit Selenium Playground"
                ),
                arguments(
                        "Chrome",
                        "latest-1",
                        platform_name,
                        "[Test-1] JUnit Selenium Playground",
                        "[Test-1] JUnit Selenium Playground"
                )
        );
    }
}