package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CoursesPageTest extends TestBase {


    private CoursesEditPage createCourse(String name, String price) {

        AdminMenuPage adminMenu = new AdminMenuPage(driver);
        adminMenu.openCourses();

        CoursesPage coursesPage = new CoursesPage(driver);
        coursesPage.clickCreateNewCourse();

        CoursesPopupPage popup = new CoursesPopupPage(driver);
        popup.enterCourseName(name);
        popup.enterPrice(price);

        return popup.clickCreateAndEdit();
    }


    @Test (priority = 0, enabled = true, description = "Create a course and display the left menu on the edit page" )
    public void shouldCreateCourseAndDisplayLeftMenuOnEditPage() {

        CoursesEditPage editPage = createCourse("Automation Course", "400");

        Assert.assertTrue(
                editPage.isPageLoaded(),
                "Course edit page did not load"
        );

        Assert.assertTrue(
                editPage.isLeftMenuVisible(),
                "Left menu is not fully visible on Course Edit page"
        );

        Assert.assertTrue(editPage.isCertificationLabelVisible(),
                "Certificates label is not visible");

        Assert.assertTrue(editPage.isRedirectUrlLabelVisible(),
                "Redirect URL label is not visible");

        Assert.assertTrue(editPage.isRedirectUrlInputVisible(),
                "Redirect URL input is not visible");

        Assert.assertTrue(editPage.isExternalOfferLinkLabelVisible(),
                "External offer link label is not visible");

        Assert.assertTrue(editPage.isExternalOfferLinkInputVisible(),
                "External offer link input is not visible");
    }


    @Test (priority = 10, enabled = true, description = "Add a variant to the course")
    public void shouldAddVariantToCourse() {

        CoursesEditPage editPage = createCourse("Symulacja lotow", "20000");

        editPage.clickAddVariantButton();

        VariantPopupPage variantPopup = new VariantPopupPage(driver);

        Assert.assertTrue(variantPopup.isPopupVisible(),
                "Variant popup is not visible");

        variantPopup.enterVariantName("Test Variant 2");

        variantPopup.enterPrice("7000");

        variantPopup.selectSalesEnabledAndDefault();

        variantPopup.selectAccessType("Bezterminowy, z natychmiastowym dostępem");

        variantPopup.clickSaveAndWaitForClose();
        Assert.assertTrue(
                editPage.isAddVariantButtonVisible(),
                "Add Variant button is not visible after saving variant");
    }


    @Test (priority = 20, enabled = true, description = "Course with promotion and sale price")
    public void shouldAddVariantWithPromotionAndSalePrice() {

        CoursesEditPage editPage = createCourse("Promo checking", "67800");

        editPage.clickAddVariantButton();

        VariantPopupPage variantPopup = new VariantPopupPage(driver);

        Assert.assertTrue(variantPopup.isPopupVisible(),
                "Variant popup is not visible");

        variantPopup.enterVariantName("Promo01");
        variantPopup.enterPrice("89870");
        variantPopup.selectSalesEnabledAndDefault();
        variantPopup.selectPromotionTypeDropdown("Bez ram czasowych");

        Assert.assertTrue(
                variantPopup.isSalePriceInputVisible(),
                "Sale price input should be visible after selecting promotion type"
        );

        variantPopup.enterSalePrice("5000");

        Assert.assertEquals(variantPopup.getSalePriceValue(),
                "5000",
                "sale price value is incorrect");

        variantPopup.clickSaveAndWaitForClose();
    }

}



