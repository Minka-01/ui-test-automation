
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // **************** WebElements ****************


    @FindBy(css = "a.edd-add-to-cart[data-action='edd_add_to_cart']")
    private WebElement orderButton;

   /* @FindBy(css = "span.edd-cart-added-alert")
    private WebElement addedToCartMessage;*/


    @FindBy(css = "a.edd_go_to_checkout")
    private WebElement goToCartButton;



    // **************** Actions on WebElements ****************

    public void clickOrderButton() {

        wait.until(ExpectedConditions.elementToBeClickable(orderButton));

        scrollToElement(orderButton);

        orderButton.click();

        wait.until(ExpectedConditions.visibilityOf(goToCartButton));

    }


    public CartPage clickGoToCartButton() {

        wait.until(ExpectedConditions.visibilityOf(goToCartButton));

        wait.until(ExpectedConditions.elementToBeClickable(goToCartButton));

        scrollToElement(goToCartButton);

        goToCartButton.click();

        return new CartPage(driver);
    }


    public CartPage orderProductAndGoToCart() {

        clickOrderButton();

        return clickGoToCartButton();
    }

    //****No usage*****

    public boolean isPageLoaded() {

        wait.until(ExpectedConditions.visibilityOf(orderButton));
        return true;
    }


}