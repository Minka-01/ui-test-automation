package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PhysicalProductsPopupPage extends BasePage {

    public PhysicalProductsPopupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //***********************WebElements***********************+


    @FindBy(id = "form-physical_products_popup_editor")
    private WebElement popupContainer;

    @FindBy(id = "name")
    private WebElement productNameInput;

    @FindBy(id = "price")
    private WebElement productPriceInput;

    @FindBy(xpath = "//button[normalize-space()='Utwórz i edytuj']")
    private WebElement createButton;

    @FindBy(xpath = "//button[normalize-space()='Anuluj']")
    private WebElement cancelButton;


    //***********************Actions on WebElements*************************+

    public boolean isPopupVisible() {
        try {
            waitForVisibility(popupContainer);
            return popupContainer.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void enterProductName(String name) {
        type(productNameInput, name);
    }

    public void enterProductPrice(String price) {
        type(productPriceInput, price);
    }

    public PhysicalProductsEditPage clickCreate() {

        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();

        wait.until(ExpectedConditions.urlContains("publigo-editor-physical-product"));

        return new PhysicalProductsEditPage(driver);
    }

//**No usage****
   public void clickCancel() {

        scrollToElement(cancelButton);

        cancelButton.click();

        wait.until(ExpectedConditions.invisibilityOf(popupContainer));
    }

}

