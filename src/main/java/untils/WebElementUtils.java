package untils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebElementUtils {
    // Method to input text into a text box
    public static void inputText(WebElement element, String text) {
        element.clear(); // Clear existing text
        element.sendKeys(text);
    }
    // Method to select an option from a dropdown by visible text
    public static void selectDropdownByVisibleText(WebElement dropdown, String visibleText) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }
}
