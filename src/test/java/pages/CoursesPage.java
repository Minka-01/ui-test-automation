package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursesPage extends BasePage {
    public CoursesPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);}

//***********************WebElements*********************************+

    @FindBy(css = "button.create-course")
    private WebElement createNewCourseButton;



//********************************Actions on WebElements**********************
    public void clickCreateNewCourse() {
        click(createNewCourseButton);
    }
}
