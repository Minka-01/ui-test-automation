package tests;

import config.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class PhysicalProductsPageTest extends TestBase {


    @Test (priority = 0, enabled = true, description = "Create a physical product")
    public void shouldCreatePhysicalProduct() {
        String productName = PropertiesReader.read("physical.product.name");
        String productPrice = PropertiesReader.read("physical.product.price");

        AdminMenuPage adminMenu = new AdminMenuPage(driver);
        adminMenu.openPhysicalProducts();

        PhysicalProductsPage productsPage = new PhysicalProductsPage(driver);
        productsPage.clickAddPhysicalProductButton();

        PhysicalProductsPopupPage popup = new PhysicalProductsPopupPage(driver);

        boolean popupVisible = popup.isPopupVisible();
        Assert.assertTrue(popupVisible, "Popup not visible");

        popup.enterProductName(productName);
        popup.enterProductPrice(productPrice);

        popup.clickCreate();

        try {
            boolean stillPopup = popup.isPopupVisible();
        } catch (Exception e) {

        }
        adminMenu.openPhysicalProducts();

        productsPage = new PhysicalProductsPage(driver);

        boolean productExists = productsPage.isPhysicalProductVisible(productName);

        Assert.assertTrue(productExists, "Product not visible in table");

        }



    @Test (priority = 10, enabled = true, description = "Purchase a physical product")
    public void shouldOpenProductAndPurchase() {

        String productName =
                PropertiesReader.read("physical.product.name");

        AdminMenuPage adminMenu = new AdminMenuPage(driver);

        adminMenu.openPhysicalProducts();

        PhysicalProductsPage productsPage = new PhysicalProductsPage(driver);

        Assert.assertTrue(productsPage.isPageLoaded());

        PhysicalProductsEditPage editPage = productsPage.clickProductByName(productName);

        editPage.enableSales();

        editPage.showProductInCatalog();

        ProductDetailsPage detailsPage =
                editPage.clickViewProduct();

        AdminBarComponent adminBar = new AdminBarComponent(driver);

        adminBar.disableAdminMode();

        CartPage cartPage = detailsPage.orderProductAndGoToCart();

        Assert.assertTrue(cartPage.isCartLoaded());

        Assert.assertTrue(cartPage.isCartWithProducts());
        Assert.assertTrue(cartPage.isCartLoaded());

        Assert.assertTrue(cartPage.isCartWithProducts());

    }


    @Test (priority = 20, enabled = false, description = "Enables sales for a physical product")
    public void shouldEnableSalesForPhysicalProduct() {

        AdminMenuPage adminMenu = new AdminMenuPage(driver);
        adminMenu.openPhysicalProducts();

        PhysicalProductsPage page = new PhysicalProductsPage(driver);

        Assert.assertTrue(page.isPageLoaded());

        page.enableSalesForFirstProduct();

        Assert.assertTrue(
                page.isSalesEnabledForFirstProduct(),
                "Sales was not enabled"
        );
    }

}







