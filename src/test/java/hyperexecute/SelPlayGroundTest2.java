package hyperexecute;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Execution(ExecutionMode.CONCURRENT)
public class SelPlayGroundTest2
{
    /*  protected static ChromeDriver driver; */
    protected WebDriver driver = null;
    static String URL = "https://lambdatest.github.io/sample-todo-app/";
    public static String status = "passed";
    String gridURL = "@hub.lambdatest.com/wd/hub";
    String user_name = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" :
            System.getenv("LT_USERNAME");
    String access_key = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" :
            System.getenv("LT_ACCESS_KEY");

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
    }

    @ParameterizedTest
    @MethodSource("browser")
    public void test_SelPlayground2(String browserName, String version, String platform,
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
            driver.findElement(By.name("li2")).click();

            /* Let's add an item in the list. */
            driver.findElement(By.id("sampletodotext")).sendKeys("Happy Testing at LambdaTest");
            driver.findElement(By.id("addbutton")).click();

            /* Let's check that the item we added is added in the list. */
            String enteredText = driver.findElement(By.xpath("//span[.='Happy Testing at LambdaTest']")).getText();
            if (enteredText.equals("Happy Testing at LambdaTest"))
            {
                System.out.println("JUnit demo on HyperExecute Grid is successful");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void TearDownClass()
    {
        driver.quit();
        System.out.println("Browser resources released");
    }

    @AfterAll
    public static void endTest()
    {
        System.out.println("JUnit execution on HyperExecute Grid complete");
    }

    static Stream<Arguments> browser()
    {
        return Stream.of(
                arguments("Chrome", "latest", "Windows 10",
                        "[Test - 5] JUnit tests on HyperExecute Grid",
                        "[Test - 5] JUnit tests on HyperExecute Grid"),
                arguments("Firefox", "latest-1", "Windows 10",
                        "[Test - 6] JUnit tests on HyperExecute Grid",
                        "[Test - 6] JUnit tests on HyperExecute Grid")
        );
    }
}
