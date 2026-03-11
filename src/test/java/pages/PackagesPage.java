package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PackagesPage extends BasePage {

    public PackagesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //*************** WebElements *****************

    @FindBy(css = "button.dynamic-table__actions-menu-button")
    private List<WebElement> actionsMenuButtons;


    //*************** Actions on WebElements*****************

    public void clickPackageDropdown(int index) {

        wait.until(ExpectedConditions.visibilityOfAllElements(actionsMenuButtons));

        WebElement dropdownButton = actionsMenuButtons.get(index);

        wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
        dropdownButton.click();
    }


    public PackagesEditPage clickEdit(int index) {

        WebElement dropdownButton = actionsMenuButtons.get(index);

        wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
        dropdownButton.click();

        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                dropdownButton.findElement(By.xpath(
                        "./ancestor::tr//a[contains(@class,'edit-bundle')]"
                ))
        ));

        editButton.click();

        return new PackagesEditPage(driver);
    }
}