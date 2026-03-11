package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminMenuPage;
import pages.PackagesPage;
import pages.PackagesEditPage;

public class PackagesPageTest extends TestBase {

    @Test (priority = 0, enabled = true, description = "Change order of products in a package")
    public void shouldChangeOrderOfProductsInPackage() {

        AdminMenuPage adminMenu = new AdminMenuPage(driver);
        adminMenu.openPackages();

        PackagesPage packagesPage = new PackagesPage(driver);

        packagesPage.clickPackageDropdown(0);

        PackagesEditPage editPage = packagesPage.clickEdit(0);

        editPage.clickPackageContentsTab();

        Assert.assertTrue(
                editPage.areProductsVisible(),
                "Products are not visible"
        );

        String firstBefore = editPage.getItemIndex(0);
        String secondBefore = editPage.getItemIndex(1);

        editPage.dragProduct(0, 1);

        editPage.clickSaveButton();

        boolean orderChanged = editPage.waitUntilOrderChanges(firstBefore);

        Assert.assertTrue(
                orderChanged,
                "Order did not change after drag and save"
        );

        String firstAfter = editPage.getItemIndex(0);
        String secondAfter = editPage.getItemIndex(1);

        Assert.assertEquals(
                firstAfter,
                secondBefore,
                "First product did not move correctly"
        );

        Assert.assertEquals(
                secondAfter,
                firstBefore,
                "Second product did not move correctly"
        );
        int countBefore = editPage.getProductsCount();

        editPage.scrollToBottom();

        editPage.clickRemoveProductIcon(0);

        editPage.waitUntilProductRemoved(countBefore - 1);

        editPage.clickSaveButton();

        Assert.assertTrue(
                editPage.isSaveConfirmationVisible(),
                "Save confirmation not visible"
        );

        int countAfter = editPage.getProductsCount();

        Assert.assertEquals(countAfter, countBefore - 1);

    }

    @Test (priority = 10, enabled = true, description = "Move the 2. product down in a package")
    public void shouldMoveSecondProductDown() {

        AdminMenuPage adminMenu = new AdminMenuPage(driver);
        adminMenu.openPackages();

        PackagesPage packagesPage = new PackagesPage(driver);
        packagesPage.clickPackageDropdown(0);

        PackagesEditPage editPage = packagesPage.clickEdit(0);

        editPage.clickPackageContentsTab();

        int countBefore = editPage.getProductsCount();

       if (countBefore < 3) {
            return;
        }


        String firstBefore = editPage.getItemIndex(0);
        String secondBefore = editPage.getItemIndex(1);
        String thirdBefore = editPage.getItemIndex(2);

        editPage.dragProduct(1, 2);

        editPage.clickSaveButton();

        String firstAfter = editPage.getItemIndex(0);
        String secondAfter = editPage.getItemIndex(1);
        String thirdAfter = editPage.getItemIndex(2);


        Assert.assertEquals(firstAfter, firstBefore,
                "First product should not change");

        Assert.assertEquals(secondAfter, thirdBefore,
                "Third product should move to second position");

        Assert.assertEquals(thirdAfter, secondBefore,
                "Second product should move to third position");

    }

}
