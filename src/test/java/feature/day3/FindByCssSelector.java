package feature.day3;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FindByCssSelector {
    public static void main(String[] args) {
        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Truy cập vào trang web
        driver.get("https://saucelabs.com/request-demo");

        // Tìm phần tử theo CSS Selector: tất cả các nút <button>
        List<WebElement> buttons = driver.findElements(By.cssSelector("button"));
        System.out.println("\nButtons:");
        for (WebElement button : buttons) {
            System.out.println(button.getText());
        }
        // Tìm phần tử theo CSS Selector: input có name là "Email"
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='Email']"));
        emailInput.sendKeys("test@example.com");

        // Đóng trình duyệt
        driver.quit();
    }
}

//    Một số CSS Selector phổ biến:
//        tagName.className:
//        Tìm thẻ với class cụ thể. Ví dụ: button.submit (tìm thẻ <button> có class là submit).
//
//        tagName#id:
//        Tìm thẻ với ID cụ thể. Ví dụ: input#email (tìm thẻ <input> có ID là email).
//
//        tagName[attribute='value']:
//        Tìm thẻ với thuộc tính và giá trị cụ thể. Ví dụ: input[name='email'].
//
//        tagName > child:
//        Tìm phần tử con trực tiếp. Ví dụ: div > p (tìm <p> là con trực tiếp của <div>).
//
//        tagName:first-child:
//        Tìm phần tử con đầu tiên. Ví dụ: li:first-child.
