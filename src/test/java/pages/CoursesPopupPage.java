package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursesPopupPage extends BasePage {
    public CoursesPopupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

//*********WebElements*****************


    @FindBy(css = "div[data-related-field='name'] input")
    private WebElement courseNameInput;

    @FindBy(css = "div[data-related-field='price'] input")
    private WebElement priceInput;

    @FindBy(id = "save-courses_popup_editor")
    private WebElement createAndEditButton;


    //**********Actions on WebElements*********

    public void enterCourseName(String name){
        type(courseNameInput, name);
    }
    public void enterPrice(String price){
        type(priceInput, price);
    }


    public CoursesEditPage clickCreateAndEdit() {
        click(createAndEditButton);
        return new CoursesEditPage(driver);
    }

//***No usage***
   public boolean isPopupVisible() {
        waitForVisibility(courseNameInput);
        return courseNameInput.isDisplayed();
    }


}







