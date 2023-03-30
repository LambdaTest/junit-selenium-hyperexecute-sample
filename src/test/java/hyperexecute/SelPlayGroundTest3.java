package hyperexecute;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class SelPlayGroundTest3 {
    /*  protected static ChromeDriver driver; */
    protected RemoteWebDriver driver = null;
    static String URL = "https://www.lambdatest.com/selenium-playground/";
    public static String status;
    String gridURL = "@hub.lambdatest.com/wd/hub";
    String user_name = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" :
            System.getenv("LT_USERNAME");
    String access_key = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" :
            System.getenv("LT_ACCESS_KEY");

    @BeforeAll
    public static void start() {
        System.out.println("Running JUnit test on HyperExecute Grid");
    }

    @BeforeEach
    public void setup() {
        System.out.println("Setting up resources to run tests on HyperExecute Grid");
    }

    public void SetUpBrowser(String browserName, String version, String platform,
                             String build, String name) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("version", version);
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("build", build);
        capabilities.setCapability("name", name);
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("video", true);
        capabilities.setCapability("console", true);

        try {
            driver = new RemoteWebDriver(new URL("https://" + user_name + ":" + access_key + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ParameterizedTest
    @MethodSource("setup_testEnvironment")
    /*
    @ParameterizedTest(name = "{index} => browserName={0}, version={1}, platform={2}, build={3}, name={4}")
    @CsvFileSource(resources = "/test-combinations/linux/junit-test-data.csv")
    */
    public void test_ToDo(String browserName, String version, String platform, String build, String name) {
        SetUpBrowser(browserName, version, platform, build, name);


        driver.navigate().to(URL);
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        try
        {
            /*Navigating to simple demo form*/
            driver.findElement(By.linkText("Simple Form Demo")).click();
            
            driver.findElement(By.cssSelector("#user-message")).sendKeys("Hello World");
            driver.findElement(By.cssSelector("#showInput")).click();

            String enteredMessage = driver.findElement(By.cssSelector("#message")).getText();
            if (enteredMessage.equals("Hello World"))
            {
                System.out.println("Hello World printed successfully");
            }
            
            driver.findElement(By.cssSelector("#sum1")).sendKeys("65");
            driver.findElement(By.cssSelector("#sum2")).sendKeys("35");
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/form[1]/button[1]")).click();
            
            String sum = driver.findElement(By.cssSelector("#message")).getText();
            if (sum.equals("100"))
            {
                System.out.println("Calculation done successfully");
            }
            
            /*Navigating to checkbox demo form*/
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,-500)", "");
            driver.findElement(By.linkText("Checkbox Demo")).click();
            Thread.sleep(1000);
            
            driver.findElement(By.cssSelector("#isAgeSelected")).click();
            
            driver.findElement(By.cssSelector("#ex1-check1")).click();
            driver.findElement(By.cssSelector("#ex1-check2")).click();
            driver.findElement(By.cssSelector("#ex1-check3")).click();
            driver.findElement(By.cssSelector("#ex1-check2")).click();
            driver.findElement(By.cssSelector("#ex1-check3")).click();
            driver.findElement(By.cssSelector("#ex1-check1")).click();
            driver.findElement(By.cssSelector("#ex1-check2")).click();
            driver.findElement(By.cssSelector("#ex1-check3")).click();
            driver.findElement(By.cssSelector("#ex1-check1")).click();
            
            driver.findElement(By.cssSelector("#box")).click();
            driver.findElement(By.cssSelector("#box")).click();
            driver.findElement(By.cssSelector("#box")).click();
            driver.findElement(By.cssSelector("#box")).click();
            driver.findElement(By.cssSelector("#box")).click();
            driver.findElement(By.cssSelector("#box")).click();
            
            /*Navigating to Radio buttons demo form*/
            driver.findElement(By.linkText("Radio Buttons Demo")).click();
            Thread.sleep(1000);
            
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[1]/div[2]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[1]/div[2]/label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[1]/div[2]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[1]/div[2]/label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[1]/div[2]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[1]/div[2]/label[2]")).click();
            driver.findElement(By.cssSelector("#buttoncheck")).click();
            
            Thread.sleep(1000);
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/label[3]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/label[3]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/label[2]")).click();
            
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/button[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/button[1]")).click();
           
            /*Navigating to different pages from side menu*/
            js.executeScript("window.scrollBy(0,-500)", "");
            driver.findElement(By.linkText("Select Dropdown List")).click();
            driver.findElement(By.linkText("Input Form Submit")).click();
            driver.findElement(By.linkText("Ajax Form Submit")).click();
            driver.findElement(By.linkText("JQuery Select dropdown")).click();
            driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/section[3]/div[1]/div[1]/div[1]/div[3]/p[1]")).click();
            driver.findElement(By.linkText("Table Pagination")).click();
            driver.findElement(By.linkText("Table Data Search")).click();
            driver.findElement(By.linkText("Table Filter")).click();
            driver.findElement(By.linkText("Table Sort & Search")).click();
            driver.findElement(By.linkText("Table Data Download")).click();
            driver.findElement(By.linkText("Table Pagination")).click();
            driver.findElement(By.linkText("Table Data Search")).click();
            
            status= "passed";
        }
        catch (Exception e)
        {
            status= "failed";
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void TearDownClass() {
        ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
        driver.quit();
        System.out.println("Browser resources released");
    }

    @AfterAll
    public static void endTest() {
        System.out.println("JUnit execution on HyperExecute Grid complete");
    }

    /* The data is not being read from CSV file */
    static Stream<Arguments> setup_testEnvironment()
    {
        String platform_name = System.getenv("TARGET_OS");
        System.out.println(platform_name);

        return Stream.of(
            arguments("Chrome", "latest-1", platform_name,
                    "[Test - 5] JUnit tests on HyperExecute Grid",
                    "[Test - 5] JUnit tests on HyperExecute Grid"),
            arguments("Firefox", "latest-2", platform_name,
                    "[Test - 6] JUnit tests on HyperExecute Grid",
                    "[Test - 6] JUnit tests on HyperExecute Grid")
        );
    }
}
