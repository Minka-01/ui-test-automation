package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AdminMenuPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public AdminMenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

  //*********WebElements************

    @FindBy(css = "a[href='admin.php?page=wp-idea-courses']")
    private WebElement coursesMenu;

    @FindBy(css = "a[href='admin.php?page=publigo-digital-products']")
    private WebElement digitalProductsMenu;

    @FindBy(css = "a[href='admin.php?page=publigo-physical-products']")
    private WebElement physicalProductsMenu;

    @FindBy(css = "a[href='admin.php?page=publigo-services']")
    private WebElement servicesMenu;

    @FindBy(css = "a[href='admin.php?page=wp-idea-tests']")
    private WebElement testsMenu;

    @FindBy(css = "a[href='admin.php?page=wp-idea-payment-history']")
    private WebElement paymentHistoryMenu;

    @FindBy(xpath = "//a[contains(@href,'publigo-sales-report')]")
    private WebElement reportsMenu;

    @FindBy(xpath = "//div[normalize-space()='Media']")
    private WebElement mediaMenu;

    @FindBy(xpath = "//a[contains(@href,'wp-idea-users')]")
    private WebElement usersMenu;

    @FindBy(css = "a[href*='page=publigo-packages']")
    private WebElement packagesMenu;


    //***********Actions on WebElements**********

    private void openMenu(WebElement menuItem) {
        wait.until(ExpectedConditions.elementToBeClickable(menuItem));
        menuItem.click();
    }

    public CoursesPage openCourses() {
        openMenu(coursesMenu);
        return new CoursesPage(driver);
    }

    public DigitalProductsPage openDigitalProducts() {
        openMenu(digitalProductsMenu);
        return new DigitalProductsPage(driver);
    }

    public PhysicalProductsPage openPhysicalProducts() {
        openMenu(physicalProductsMenu);
        return new PhysicalProductsPage(driver);
    }

    public ServicesPage openServices() {
        openMenu(servicesMenu);
        return new ServicesPage(driver);
    }

    public TestsPage openTests() {
        openMenu(testsMenu);
        return new TestsPage(driver);
    }


    public PackagesPage openPackages() {
        openMenu(packagesMenu);
        return new PackagesPage(driver);
    }

}
