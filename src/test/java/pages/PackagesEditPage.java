
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class PackagesEditPage extends BasePage {

    private Actions actions;

    public PackagesEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

//************+WebElements******************

    @FindBy(id = "contents")
    private WebElement packageContentsTab;

    @FindBy(css = "button.reset-data")
    private WebElement cancelButton;

    @FindBy(css = "button.add-row")
    private WebElement addProductButton;

    private final By removeButtonLocator =
            By.cssSelector("button[title='remove_rule']");


//************Actions on WebElements******************

    public void clickPackageContentsTab() {

        wait.until(ExpectedConditions.elementToBeClickable(packageContentsTab));
        packageContentsTab.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("tbody[role='rowgroup'] tr")
        ));
    }

    public int getProductsCount() {

        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("tbody[role='rowgroup'] tr")
                )
        ).size();
    }

    public void scrollToBottom() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "window.scrollTo(0, document.body.scrollHeight);"
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("button.save-data")
        ));
    }

    public void dragProduct(int sourceIndex, int targetIndex) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script =
                "const tbody = document.querySelector('tbody[role=\"rowgroup\"]');" +
                        "if(!tbody) return 'no_tbody';" +

                        "const rows = Array.from(tbody.querySelectorAll('tr'));" +
                        "const src = rows[" + sourceIndex + "];" +
                        "const tgt = rows[" + targetIndex + "];" +
                        "if(!src || !tgt) return 'no_rows';" +

                        "if (" + targetIndex + " > " + sourceIndex + ") {" +
                        "  tbody.insertBefore(src, tgt.nextSibling);" +
                        "} else {" +
                        "  tbody.insertBefore(src, tgt);" +
                        "}" +

                        "function trigger(el, name){" +
                        "  try{ if(window.jQuery) jQuery(el).trigger(name); } catch(e){};" +
                        "  try{ el.dispatchEvent(new Event(name, {bubbles:true})); } catch(e){};" +
                        "}" +

                        "trigger(tbody,'sortupdate');" +
                        "trigger(tbody,'update');" +

                        "tbody.querySelectorAll('select').forEach(s => { try{ s.dispatchEvent(new Event('change',{bubbles:true})); }catch(e){} });" +

                        "tbody.querySelectorAll('tr').forEach(r => { try{ r.dispatchEvent(new Event('change',{bubbles:true})); }catch(e){} });" +

                        "const btn = document.querySelector('button.save-data');" +
                        "if(btn){" +
                        "  try{ btn.disabled = false; btn.removeAttribute('disabled'); btn.setAttribute('aria-disabled','false'); }catch(e){}" +
                        "  try{ btn.classList.remove('disabled'); }catch(e){}" +
                        "}" +

                        "return 'moved';";

        Object result = js.executeScript(script);

        By indexLocator = By.cssSelector("span.item-index");

        wait.until(driver -> {
            List<WebElement> idxs = driver.findElements(indexLocator);
            return idxs.size() >= 2;
        });

        By saveBtn = By.cssSelector("button.save-data");
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
    }

    public boolean areProductsVisible() {

        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("tbody[role='rowgroup'] tr")
                )
        );

        return rows.size() > 0;
    }

    public void clickSaveButton() {

        By saveButtonLocator = By.cssSelector("button.save-data");

        WebElement saveButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(saveButtonLocator)
        );

        wait.until(driver -> {
            WebElement btn = driver.findElement(saveButtonLocator);
            return btn.isEnabled();
        });

        wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));

        driver.findElement(saveButtonLocator).click();
    }

    public boolean waitUntilOrderChanges(String oldFirstValue) {

        By indexLocator = By.cssSelector("span.item-index");

        return wait.until(driver -> {

            List<WebElement> indexes = driver.findElements(indexLocator);

            if (indexes.isEmpty()) return false;

            String newFirstValue = indexes.get(0).getText();

            return !newFirstValue.equals(oldFirstValue);
        });
    }

    public String getItemIndex(int index) {
        By locator = By.cssSelector("span.item-index");
        List<WebElement> indexes = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        );
        return indexes.get(index).getText().trim();
    }

    public void clickRemoveProductIcon(int index) {

        By rowsLocator = By.cssSelector("tbody[role='rowgroup'] tr");

        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(rowsLocator)
        );

        WebElement row = rows.get(index);

        WebElement removeButton = row.findElement(
                By.cssSelector("button[title='remove_rule']")
        );

        wait.until(ExpectedConditions.elementToBeClickable(removeButton));

        removeButton.click();
    }

    public boolean waitUntilProductRemoved(int expectedCount) {

        By rowsLocator = By.cssSelector("tbody[role='rowgroup'] tr");

        return wait.until(driver ->
                driver.findElements(rowsLocator).size() == expectedCount
        );
    }

    public boolean isSaveConfirmationVisible() {

        By confirmation =
                By.xpath("//*[contains(text(),'Ustawienia zostały zapisane!')]");

        try {
            WebElement msg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(confirmation)
            );
            return msg.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    //***No usage***

    public String getProductName(int index) {
        List<WebElement> rows = driver.findElements(
                By.cssSelector("tbody[role='rowgroup'] tr select")
        );
        return rows.get(index).getText();
    }

    public void scrollToProductRow(int index){
        By rowsLocator = By.cssSelector("tbody[role='rowgroup'] tr select");
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rowsLocator));
        WebElement row = rows.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});, row)");
        wait.until(ExpectedConditions.elementToBeClickable(row));
    }
}


