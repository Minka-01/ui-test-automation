package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminMenuPage;

public class AdminMenuTest extends TestBase {


    private AdminMenuPage menu;

    @BeforeMethod
    public void setUpMenu() {
        menu = new AdminMenuPage(driver);
    }

    @Test(priority = 0, enabled = true, description = "Verify Courses page title")
    public void coursesTitle() {
        menu.openCourses();
        Assert.assertTrue(driver.getTitle().contains("Kursy"));
    }

    @Test(priority = 10, enabled = true, description = "Verify Courses page URL")
    public void coursesUrl() {
        menu.openCourses();
        Assert.assertTrue(driver.getCurrentUrl().contains("wp-idea-courses"));
    }

    @Test(priority = 20, enabled = true, description = "Verify Digital Products page title")
    public void digitalTitle() {
        menu.openDigitalProducts();
        Assert.assertTrue(driver.getTitle().contains("Produkty cyfrowe"));
    }

    @Test(priority = 30, enabled = true, description = "Verify Digital Products page URL")
    public void digitalUrl() {
        menu.openDigitalProducts();
        Assert.assertTrue(driver.getCurrentUrl().contains("publigo-digital-products"));
    }

    @Test(priority = 40, enabled = true, description = "Verify Physical Products page title")
    public void physicalTitle() {
        menu.openPhysicalProducts();
        Assert.assertTrue(driver.getTitle().contains("Produkty fizyczne"));
    }

    @Test(priority = 50, enabled = true, description = "Verify Physical Products page URL")
    public void physicalUrl() {
        menu.openPhysicalProducts();
        Assert.assertTrue(driver.getCurrentUrl().contains("publigo-physical-products"));
    }

    @Test(priority = 60, enabled = true, description = "Verify Services page title")
    public void servicesTitle() {
        menu.openServices();
        Assert.assertTrue(
                driver.getTitle().contains("Usługi"),
                "Services page title is incorrect"
        );
    }

    @Test(priority = 70, enabled = true, description = "Verify Services page URL")
    public void servicesUrl() {
        menu.openServices();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("publigo-services"),
                "Services page URL is incorrect"
        );
    }

    @Test(priority = 80, enabled = true, description = "Verify Tests page URL")
    public void testsUrl() {
        menu.openTests();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("wp-idea-tests"),
                "Tests URL is incorrect"
        );
    }
}