package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AdminBarComponent extends BasePage {

    public AdminBarComponent(WebDriver driver) {
        super(driver);
    }

    public void disableAdminMode() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "document.body.classList.remove('admin-bar');"
        );

        js.executeScript(
                "var adminBar = document.getElementById('wpadminbar');" +
                        "if(adminBar){ adminBar.remove(); }"
        );

        js.executeScript(
                "document.documentElement.style.marginTop='0px';"
        );


    }
}