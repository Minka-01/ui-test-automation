package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class VariantPopupPage extends BasePage {

    public VariantPopupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

//***********************WebElements*********************************+

    @FindBy(xpath = "//h2[normalize-space()='Dodaj wariant']")
    private WebElement popupTitle;

    @FindBy(name = "name_price_option")
    private WebElement variantNameInput;

    @FindBy(id = "price")
    private WebElement priceInput;


    @FindBy(xpath = "//input[@value='sales_enabled']")
    private WebElement salesEnabledRadioButton;
    @FindBy(xpath = "//input[@value='sales_enabled_and_default_option']")
    private WebElement salesEnabledAndDefaultRadioButton;
    @FindBy(xpath = "//input[@value='sales_disabled']")
    private WebElement salesDisabledRadioButton;


    @FindBy(id = "promotion_type")
    private WebElement promotionTypeDropdown;
    @FindBy(id = "access_start_date")
    private WebElement accessStartDateInput;
    @FindBy(id = "access_start_hour")
    private WebElement accessStartHourDropdown;
    @FindBy(id = "access_start_minutes")
    private WebElement accessStartMinutesDropdown;
    @FindBy(id = "access_end_date")
    private WebElement accessEndDateInput;
    @FindBy(id = "access_end_hour")
    private WebElement accessEndHourDropdown;
    @FindBy(id = "access_end_minutes")
    private WebElement accessEndMinutesDropdown;
    @FindBy(id = "access_time")
    private WebElement accessTimeInput;
    @FindBy(id = "access_time_unit")
    private WebElement accessTimeUnitDropdown;



    @FindBy(id = "sale_price")
    private WebElement salePriceInput;

    @FindBy(id = "sale_price_date_from_date")
    private WebElement salePriceDateFrom;

    @FindBy(id = "sale_price_date_to_date")
    private WebElement salePriceDateTo;

    @FindBy(id = "sale_price_date_from_hour")
    private WebElement salePriceDateFromHour;

    @FindBy(id = "sale_price_date_to_hour")
    private WebElement salePriceDateToHour;

    @FindBy(name = "bpmj_eddcm_purchase_limit_items_left")
    private WebElement purchaseLimitFromInput;

    @FindBy(name = "bpmj_eddcm_purchase_limit")
    private WebElement purchaseLimitToInput;

    @FindBy(name = "access_type")
    private WebElement accessTypeDropdown;

    @FindBy(css = ".wpi-popup.wpi-popup--standard .wpi-popup__footer .wpi-button--main")
    private WebElement saveButton;

    @FindBy(css = ".wpi-popup")
    private WebElement popupContainer;

    @FindBy(xpath = "//button[normalize-space()='Anuluj']")
    private WebElement cancelButton;


    //************************Actions on WebElements*******************************

    public boolean isPopupVisible() {
        waitForVisibility(popupTitle);
        return popupTitle.isDisplayed();
    }

    public void enterVariantName(String name) {
        type(variantNameInput, name);
    }

    public void enterPrice(String price) {
        type(priceInput, price);
    }

    public void selectSalesEnabledAndDefault() {
        click(salesEnabledAndDefaultRadioButton);
    }
    public boolean isSalePriceInputVisible() {
        try {
            waitForVisibility(salePriceInput);
            return salePriceInput.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void selectPromotionTypeDropdown(String visibleText) {

        scrollToElement(promotionTypeDropdown);

        waitForVisibility(promotionTypeDropdown);

        Select select = new Select(promotionTypeDropdown);

        select.selectByVisibleText(visibleText);
    }

    public void selectAccessType(String visibleText) {
        scrollToElement(accessTypeDropdown);
        waitForVisibility(accessTypeDropdown);

        Select select = new Select(accessTypeDropdown);
        select.selectByVisibleText(visibleText);
    }


    public void enterSalePrice(String price) {
        waitForVisibility(salePriceInput);
        salePriceInput.clear();
        salePriceInput.sendKeys(price);
    }

    public void clickSaveAndWaitForClose() {

        scrollToElement(saveButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", saveButton);

        wait.until(ExpectedConditions.invisibilityOf(popupContainer));
    }

    public String getSalePriceValue() {
        waitForVisibility(salePriceInput);
        return salePriceInput.getAttribute("value");
    }

    //****No usage***

    public void selectSalesEnabled() {
        click(salesEnabledRadioButton);
    }

    public void selectSalesDisabled() {
        click(salesDisabledRadioButton);
    }

    public boolean isSalePriceDateFromVisible() {
        return salePriceDateFrom.isDisplayed();
    }

    public boolean isSalePriceDateFromHourVisible() {
        return salePriceDateFromHour.isDisplayed();
    }

    public boolean isSalePriceDateToVisible() {
        return salePriceDateTo.isDisplayed();
    }

    public boolean isSalePriceDateToHourVisible() {
        return salePriceDateToHour.isDisplayed();
    }

    public void enterSalePriceDateFrom(String date) {
        waitForVisibility(salePriceDateFrom);
        salePriceDateFrom.clear();
        salePriceDateFrom.sendKeys(date);
    }

    public void enterSalePriceDateTo(String date) {
        waitForVisibility(salePriceDateTo);
        salePriceDateTo.clear();
        salePriceDateTo.sendKeys(date);
    }
    public void selectSalePriceDateFromHour(String hour) {
        waitForVisibility(salePriceDateFromHour);
        Select select = new Select(salePriceDateFromHour);
        select.selectByVisibleText(hour);
    }

    public void selectSalePriceDateToHour(String hour) {
        waitForVisibility(salePriceDateToHour);
        Select select = new Select(salePriceDateToHour);
        select.selectByVisibleText(hour);
    }


    public void setAvailability(String from, String to) {
        type(purchaseLimitFromInput, from);
        type(purchaseLimitToInput, to);
    }

    public void clickCancel() {
        click(cancelButton);
        wait.until(ExpectedConditions.invisibilityOf(popupContainer));
    }
    public void selectAccessTimeUnit(String visibleText) {

        waitForVisibility(accessTimeUnitDropdown);

        Select select = new Select(accessTimeUnitDropdown);

        select.selectByVisibleText(visibleText);
    }
    public void enterAccessTime(String value) {

        waitForVisibility(accessTimeInput);

        accessTimeInput.clear();

        accessTimeInput.sendKeys(value);
    }
    public void enterAccessStartDate(String date) {

        waitForVisibility(accessStartDateInput);

        accessStartDateInput.clear();

        accessStartDateInput.sendKeys(date);
    }

    public void enterAccessEndDate(String date) {

        waitForVisibility(accessEndDateInput);

        accessEndDateInput.clear();

        accessEndDateInput.sendKeys(date);
    }

    public void selectAccessStartHour(String hour) {

        waitForVisibility(accessStartHourDropdown);

        Select select = new Select(accessStartHourDropdown);

        select.selectByVisibleText(hour);
    }

    public void selectAccessEndHour(String hour) {

        waitForVisibility(accessEndHourDropdown);

        Select select = new Select(accessEndHourDropdown);

        select.selectByVisibleText(hour);
    }


    public void selectAccessStartMinutes(String minutes) {

        waitForVisibility(accessStartMinutesDropdown);

        Select select = new Select(accessStartMinutesDropdown);

        select.selectByVisibleText(minutes);
    }

    public void selectAccessEndMinutes(String minutes) {

        waitForVisibility(accessEndMinutesDropdown);

        Select select = new Select(accessEndMinutesDropdown);

        select.selectByVisibleText(minutes);
    }

}





