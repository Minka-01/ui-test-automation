package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PhysicalProductsPage extends BasePage {

    public PhysicalProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ********************WebElements*********************

    @FindBy(css = "button.create-physical-products")
    private WebElement addPhysicalProductButton;

    @FindBy(xpath = "(//td[contains(@class,'type-checkbox')]//input[@type='checkbox'])[1]")
    private WebElement firstProductSalesCheckbox;

    @FindBy(xpath = "//button[contains(@class,'dynamic-table__header__buttons__button--dropdown')]")
    private WebElement actionsDropdownButton;

    @FindBy(xpath = "//button[contains(@class,'status-physical-product')]")
    private WebElement toggleSalesButton;

    @FindBy(xpath = "(//td[contains(@class,'type-default')]//a)[1]")
    private WebElement firstProductNameLink;

    private By firstProductSalesStatus =
            By.xpath("(//td[contains(@class,'type-status')]//span)[1]");

    private By productNameInTable(String productName) {
        return By.xpath("//div[@id='table-view-container']//a[normalize-space()='" + productName + "']");
    }


    // **************Actions on WebElements*************

    public boolean isPageLoaded() {

        wait.until(ExpectedConditions.visibilityOf(addPhysicalProductButton));
        return addPhysicalProductButton.isDisplayed();

    }

    public void clickAddPhysicalProductButton() {

        wait.until(ExpectedConditions.elementToBeClickable(addPhysicalProductButton));
        addPhysicalProductButton.click();

    }

    public boolean isPhysicalProductVisible(String productName) {
        try {
            By productLocator = By.xpath(
                    "//div[@id='table-view-container']//a[normalize-space()='" + productName + "']"
            );

            wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));

            return driver.findElement(productLocator).isDisplayed();

        } catch (TimeoutException e) {
            return false;
        }
    }

    public void enableSalesForFirstProduct() {

        wait.until(ExpectedConditions.elementToBeClickable(firstProductSalesCheckbox));
        firstProductSalesCheckbox.click();

        wait.until(ExpectedConditions.elementToBeClickable(actionsDropdownButton));
        actionsDropdownButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(toggleSalesButton));
        toggleSalesButton.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                firstProductSalesStatus,
                "Włączona"
        ));
    }


    public boolean isSalesEnabledForFirstProduct() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductSalesStatus));

        String status = driver.findElement(firstProductSalesStatus).getText();

        return status.contains("Włączona");

    }

    public PhysicalProductsEditPage clickProductByName(String productName) {

        By productLink = By.xpath("//a[normalize-space()='" + productName + "']");

        wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();

        return new PhysicalProductsEditPage(driver);
    }

    //***No usage****
    public void clickFirstProduct() {

        wait.until(ExpectedConditions.elementToBeClickable(firstProductNameLink));

        firstProductNameLink.click();
    }
}




