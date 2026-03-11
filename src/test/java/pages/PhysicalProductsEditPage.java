package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Set;

public class PhysicalProductsEditPage extends BasePage {

    public PhysicalProductsEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ************** WebElements ******************


    @FindBy(css = "label.switch[for='sales_disabled']")
    private WebElement salesToggle;

    @FindBy(css = "label.switch[for='hide_from_list']")
    private WebElement showInCatalogToggle;

    @FindBy(css = "a.product-offer-preview-button")
    private WebElement viewProductButton;


    // ************** Actions on WebElements ******************

    public void enableSales() {

        wait.until(ExpectedConditions.visibilityOf(salesToggle));

        scrollToElement(salesToggle);

        wait.until(ExpectedConditions.elementToBeClickable(salesToggle));

        WebElement input = driver.findElement(By.id("sales_disabled"));

        if (!input.isSelected()) {
            salesToggle.click();
        }
    }

    public void showProductInCatalog() {

        wait.until(ExpectedConditions.visibilityOf(showInCatalogToggle));

        scrollToElement(showInCatalogToggle);

        wait.until(ExpectedConditions.elementToBeClickable(showInCatalogToggle));

        WebElement input = driver.findElement(By.id("hide_from_list"));

        if (!input.isSelected()) {
            showInCatalogToggle.click();
        }
    }

    public ProductDetailsPage clickViewProduct() {

        System.out.println("STEP: Clicking View Product");

        String originalWindow = driver.getWindowHandle();

        wait.until(ExpectedConditions.visibilityOf(viewProductButton));


        try {
            ((JavascriptExecutor) driver).executeScript(
                    "var bar = document.getElementById('wpadminbar'); if(bar){bar.style.display='none';}");
            System.out.println("STEP: Admin bar hidden");
        } catch (Exception ignored) {}

        try {

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    viewProductButton);

            wait.until(ExpectedConditions.elementToBeClickable(viewProductButton));

            new Actions(driver)
                    .moveToElement(viewProductButton)
                    .pause(Duration.ofMillis(200))
                    .click()
                    .perform();

            System.out.println("STEP: Clicked using Actions");

        }
        catch (Exception e) {

            System.out.println("WARNING: Actions click failed, using JS click");

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    viewProductButton);
        }

        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for (String window : driver.getWindowHandles()) {

            if (!window.equals(originalWindow)) {

                driver.switchTo().window(window);

                break;
            }
        }

        wait.until(ExpectedConditions.or(

                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a.edd-add-to-cart")),

                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a.edd-go-to-checkout"))

        ));

        return new ProductDetailsPage(driver);
    }


}

