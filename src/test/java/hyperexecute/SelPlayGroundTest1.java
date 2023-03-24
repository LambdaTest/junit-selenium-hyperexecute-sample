package hyperexecute;

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.provider.CsvFileSource;

@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class SelPlayGroundTest1
{
    protected RemoteWebDriver driver = null;
    static String URL = "https://lambdatest.github.io/sample-todo-app/";
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

    public void SetUpBrowser(String browserName, String version, String platform,
                             String build, String name)
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
    /*
    @ParameterizedTest(name = "{index} => browserName={0}, version={1}, platform={2}, build={3}, name={4}")
    @CsvFileSource(resources = "/test-combinations/win/junit-test-data.csv")
    */
    public void test_SelPlayground(String browserName, String version, String platform,
                                     String build, String name)
    {
        SetUpBrowser(browserName, version, platform, build, name);

        String methodName = Thread.currentThread()
                .getStackTrace()[1]
                .getMethodName();

        driver.navigate().to(URL);
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        try
        {
            /* Let's mark done first two items in the list. */
            driver.findElement(By.name("li1")).click();
            Thread.sleep(2000);

            driver.findElement(By.name("li2")).click();
            Thread.sleep(2000);

            /* Let's add an item in the list. */
            driver.findElement(By.id("sampletodotext")).sendKeys("Checking my test work");
            driver.findElement(By.id("addbutton")).click();
            Thread.sleep(1000);
          
            /* Let's add an item in the list. */
            driver.findElement(By.id("sampletodotext")).sendKeys("Writing test cases");
            Thread.sleep(1000);
            driver.findElement(By.id("addbutton")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("sampletodotext")).sendKeys("Added new item");
            Thread.sleep(1000);
            driver.findElement(By.id("addbutton")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("sampletodotext")).sendKeys("Adding item for sample test");
            Thread.sleep(1000);
            driver.findElement(By.id("addbutton")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("sampletodotext")).sendKeys("Adding checking if code has correct output");
            Thread.sleep(1000);
            driver.findElement(By.id("addbutton")).click();
            Thread.sleep(1000);
            
            driver.findElement(By.id("sampletodotext")).sendKeys("Ensuring test quits");
            Thread.sleep(1000);
            driver.findElement(By.id("addbutton")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("sampletodotext")).sendKeys("Happy Testing at LambdaTest");
            Thread.sleep(1000);
            driver.findElement(By.id("addbutton")).click();
            Thread.sleep(1000);

            driver.findElement(By.name("li6")).click();
            Thread.sleep(1000);

            driver.findElement(By.name("li7")).click();
            Thread.sleep(1000); 
            
            driver.findElement(By.name("li8")).click();
            Thread.sleep(1000);

            driver.findElement(By.name("li9")).click();
            Thread.sleep(1000);

            driver.findElement(By.name("li10")).click();
            Thread.sleep(1000);

            driver.findElement(By.name("li11")).click();
            Thread.sleep(1000);
            
            
            
            /* Let's check that the item we added is added in the list. */
            String enteredText = driver.findElement(By.xpath("//span[.='Happy Testing at LambdaTest']")).getText();
            if (enteredText.equals("Happy Testing at LambdaTest"))
            {
                System.out.println("JUnit demo on HyperExecute Grid is successful");
            }
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
                    "[Test - 3] JUnit tests on HyperExecute Grid",
                    "[Test - 3] JUnit tests on HyperExecute Grid"),
            arguments("Microsoft Edge", "latest-1", platform_name,
                    "[Test - 4] JUnit tests on HyperExecute Grid",
                    "[Test - 4] JUnit tests on HyperExecute Grid")
        );
    }
}
