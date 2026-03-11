package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }
//**********WebElements*****************

    // *******Cart************

    private By cartTable = By.id("edd_checkout_cart");

    private By emptyCartMessage = By.cssSelector(".edd_empty_cart");

    private By cartItems =
            By.cssSelector("#edd_checkout_cart tr.edd_cart_item");

    private By cartItemTitle =
            By.cssSelector(".split-cart-item-title");


    // ************ Checkout form ************

    private By emailField =
            By.cssSelector("input[type='email']");

    private By firstNameField =
            By.cssSelector("input[name='edd_first']");

    private By lastNameField =
            By.cssSelector("input[name='edd_last']");

    private By phoneField =
            By.cssSelector("input[name='edd_phone']");

    private By nipField =
            By.cssSelector("input[name='edd_nip']");

//******Payment section******

    private By paymentSection =
            By.xpath("//h3[contains(text(),'Wybierz sposób płatności')]");

    private By payPalOption =
            By.cssSelector("img[alt='PayPal']");

    private By payUOption =
            By.xpath("//button[contains(.,'PayU')]");

    private By tpayOption =
            By.xpath("//button[contains(.,'tpay')]");

    private By totalPrice =
            By.xpath("//*[contains(text(),'Łącznie')]");


//*********Actions on WebElements************

    public boolean isCartLoaded() {

        System.out.println("STEP: Waiting for Cart Page");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(cartTable),
                ExpectedConditions.presenceOfElementLocated(emptyCartMessage)
        ));

        System.out.println("STEP: Cart Page loaded");

        return true;
    }

    public boolean isCartWithProducts() {

        return driver.findElements(
                By.cssSelector("#edd_checkout_cart tr.edd_cart_item")
        ).size() > 0;
    }

    //***No usage***
    public boolean isCheckoutFormVisible() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));

        return true;
    }

    public boolean isPaymentSectionVisible() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentSection));

        return true;
    }

    public boolean isTotalPriceVisible() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));

        return true;
    }

}




