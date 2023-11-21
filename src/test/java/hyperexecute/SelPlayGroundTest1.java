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
public class SelPlayGroundTest1
{
    protected RemoteWebDriver driver = null;
    static String URL = "https://www.lambdatest.com/selenium-playground/";
    public static String status;
    String gridURL = "@hub.lambdatest.com/wd/hub";
    String user_name = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" :
            System.getenv("LT_USERNAME");
    String access_key = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" :
            System.getenv("LT_ACCESS_KEY");
    String test_platform = System.getenv("TEST_OS");
    public static String csv_source = "/test-combinations/win/junit-test-data.csv";

    @BeforeAll
    public static void start()
    {
        System.out.println("Running JUnit test on HyperExecute Grid");
    }

    @BeforeEach
    public void setup()
    {
        System.out.println("Setting up resources to run tests on HyperExecute Grid");
    }

    public void SetUpBrowser(String browserName, String version, String platform, String build, String name)
    {
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

        try
        {
            driver = new RemoteWebDriver(new URL("https://" + user_name + ":" + access_key + gridURL), capabilities);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Invalid grid URL");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        csv_source = "/test-combinations/win/junit-test-data.csv";
    }

    @ParameterizedTest
    @MethodSource("setup_testEnvironment")
    public void test_SelPlayground(String browserName, String version, String platform, String build, String name)
    {
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
            driver.findElement(By.xpath("//*[@id='gettotal']/button")).click();
            
            String sum = driver.findElement(By.cssSelector("#message")).getText();
            if (sum.equals("100"))
            {
                System.out.println("Calculation done successfully");
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            driver.navigate().to("https://www.lambdatest.com/selenium-playground/checkbox-demo");
            js.executeScript("window.scrollBy(0,-500)", "");
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
            driver.navigate().to("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
      
            Thread.sleep(1000);
            
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]//label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]//label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]//label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]//label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]//label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]//label[2]")).click();
            driver.findElement(By.cssSelector("#buttoncheck")).click();
            
            Thread.sleep(1000);
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div/div[2]//div[1]/label")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div/div[2]//div[2]/label")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div/div[2]//div[3]/label")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div/div[2]//div[1]/label")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div/div[2]//div[2]/label")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div/div[2]//div[3]/label")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div/div[2]//div[1]/label")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div/div[2]//div[2]/label")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div/div[2]//div[3]/label")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div/div[2]//div[1]/label")).click();
            
            driver.findElement(By.xpath("//body/div[@id='__next']/div/section[2]/div/div/div/div[3]/div/div/div[1]/div[1]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div/section[2]/div/div/div/div[3]/div/div/div[1]/div[1]/label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div/section[2]/div/div/div/div[3]/div/div/div[1]/div[1]/label[3]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div/section[2]/div/div/div/div[3]/div/div/div[1]/div[2]/label[1]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div/section[2]/div/div/div/div[3]/div/div/div[1]/div[2]/label[2]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div/section[2]/div/div/div/div[3]/div/div/div[1]/div[2]/label[3]")).click();
            driver.findElement(By.xpath("//body/div[@id='__next']/div/section[2]/div/div/div/div[3]/div/div/div[1]/button")).click();
            /*Navigating to different pages from side menu*/
            js.executeScript("window.scrollBy(0,-500)", "");
            driver.navigate().to("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");
            driver.findElement(By.id("select-demo")).click();


            status= "passed";
        }
        catch (Exception e)
        {
            status= "failed";
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void TearDownClass()
    {
        ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
        driver.quit();
        System.out.println("Browser resources released");
    }

    @AfterAll
    public static void endTest()
    {
        System.out.println("JUnit execution on HyperExecute Grid complete");
    }

    /* The data is not being read from CSV file */
    static Stream<Arguments> setup_testEnvironment()
    {
        String platform_name = System.getenv("TARGET_OS");
        System.out.println(platform_name);

        return Stream.of(
            arguments("Microsoft Edge", "latest", platform_name,
                    "[Test - 1] JUnit tests on HyperExecute Grid",
                    "[Test - 1] JUnit tests on HyperExecute Grid"),
            arguments("Microsoft Edge", "latest-1", platform_name,
                    "[Test - 2] JUnit tests on HyperExecute Grid",
                    "[Test - 2] JUnit tests on HyperExecute Grid")
        );
    }
}


