package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursesEditPage extends BasePage {
    public CoursesEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

//***********************WebElements*********************************+
    @FindBy (id="general")
    private WebElement generalTab;

    @FindBy (id="structure")
    private WebElement structureTab;

    @FindBy (id="link_generator")
    private WebElement linkGeneratorTab;

    @FindBy (id="invoices")
    private WebElement invoicesTab;

    @FindBy (id="discount_code")
    private WebElement discountTab;

   @FindBy(xpath = "//label[@for='disable_certificates']")
   private WebElement certificationLabel;

   @FindBy(xpath = "//label[@for='redirect_url']")
   private WebElement redirectUrlLabel;

    @FindBy(id = "redirect_url")
    private WebElement redirectUrlInput;

   @FindBy(xpath = "//label[@for='custom_purchase_link']")
   private WebElement externalOfferLinkLabel;

    @FindBy(id = "custom_purchase_link")
    private WebElement externalOfferLinkInput;

    @FindBy(xpath = "//span[text()='Warianty']")
    private WebElement variantsLabel;

    @FindBy(xpath = "//th[normalize-space()='Cena']")
    private WebElement priceHeader;

    @FindBy(xpath = "//th[normalize-space()='Promocja']")
    private WebElement promotionHeader;

    @FindBy(xpath= "//th[normalize-space()='Dostępność']")
    private WebElement availabilityHeader;

    @FindBy(xpath = "//th[normalize-space()='Dostęp do kursu']")
    private WebElement accessHeader;

    @FindBy(xpath = "//th[normalize-space()='Płatności cykliczne']")
    private WebElement recurringPaymentsHeader;

    @FindBy(xpath = "//button[@title='Edytuj']")
    private WebElement editButton;

   @FindBy(css = "button.add-row")
   private WebElement addVariantButton;



    //**************  Actions on WebElements***************


    public boolean isPageLoaded() {
        waitForVisibility(generalTab);
        return generalTab.isDisplayed();
    }

    public boolean isLeftMenuVisible() {

        waitForVisibility(generalTab);

        return generalTab.isDisplayed()
                && structureTab.isDisplayed()
                && linkGeneratorTab.isDisplayed()
                && invoicesTab.isDisplayed()
                && discountTab.isDisplayed();
    }

    public boolean isCertificationLabelVisible() {
        waitForVisibility(certificationLabel);
        return certificationLabel.isDisplayed();
    }

    public boolean isRedirectUrlLabelVisible() {
        waitForVisibility(redirectUrlLabel);
        return redirectUrlLabel.isDisplayed();
    }

    public boolean isRedirectUrlInputVisible() {
        waitForVisibility(redirectUrlInput);
        return redirectUrlInput.isDisplayed();
    }


    public boolean isExternalOfferLinkLabelVisible() {
        waitForVisibility(externalOfferLinkLabel);
        return externalOfferLinkLabel.isDisplayed();
    }

    public boolean isExternalOfferLinkInputVisible() {
        waitForVisibility(externalOfferLinkInput);
        return externalOfferLinkInput.isDisplayed();
    }

    public boolean isAddVariantButtonVisible() {
        waitForVisibility(addVariantButton);
        return addVariantButton.isDisplayed();
    }


    public void clickAddVariantButton() {
        click(addVariantButton);
    }

//****No usage****

    public boolean isVariantsLabelVisible() {
        waitForVisibility(variantsLabel);
        return variantsLabel.isDisplayed();
    }
    public boolean areVariantHeadersVisible() {
        waitForVisibility(priceHeader);

        return priceHeader.isDisplayed()
                && promotionHeader.isDisplayed()
                && availabilityHeader.isDisplayed()
                && accessHeader.isDisplayed()
                && recurringPaymentsHeader.isDisplayed();
    }

    public boolean isEditButtonVisible() {
        waitForVisibility(editButton);
        return editButton.isDisplayed();
    }


}
























