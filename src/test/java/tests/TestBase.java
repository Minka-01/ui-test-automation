package tests;
import config.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import pages.LoginPage;


import java.time.Duration;

public class TestBase {
    // browser initialization
    public WebDriver driver;

    //loading parameters from the config file

    private static final String browser = PropertiesReader.read("browser");
    private static final String url = PropertiesReader.read("url");

    @BeforeMethod
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions = new EdgeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        switch (browser.toLowerCase()) {
            case "chrome":
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-search-engine-choice-screen");
                chromeOptions.addArguments("--disable-application-cache");

                // incognito mode
                //chromeOptions.addArguments("--incognito");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":

                edgeOptions.addArguments("--start-maximized");

                edgeOptions.addArguments("--disable-search-engine-choice-screen");

                edgeOptions.addArguments("--incognito");

                edgeOptions.addArguments("--disable-application-cache");

                driver = new EdgeDriver(edgeOptions);
                break;

            case "firefox":

                firefoxOptions.addArguments("--start-maximized");

                driver = new FirefoxDriver(firefoxOptions);
                break;

                //Browser not supported
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }
        // Implicit waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        //Navigation to the selected website
        driver.get(url);

        //Login common for all tests
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                PropertiesReader.read("username"),
                PropertiesReader.read("password")
        );

        //Wait until login redirect completes
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("wp-login")
        ));

    }

        @AfterMethod
        public void cleanUp() {

        // closing the browser
        driver.close();
        driver.quit();
    }
}

