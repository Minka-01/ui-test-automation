package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ********WebElements************

    @FindBy(id = "user_login")
    private WebElement usernameInput;

    @FindBy(id = "user_pass")
    private WebElement passwordInput;

    @FindBy(id = "wp-submit")
    private WebElement loginButton;


//********************Actions on WebElements***************

    public void login(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(usernameInput));

        usernameInput.clear();
        usernameInput.sendKeys(username);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginButton.click();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("wp-login")
        ));
    }
}
