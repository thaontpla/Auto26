package feature.day5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ui.RequestDemoPageUI;
import untils.ExcelUtils;

import java.util.List;
import java.util.Map;

public class BTVNBookYourDemoHereV2Test {
    public static void main(String[] args) throws InterruptedException {
        String excelFilePath = "book.xlsx";
        String sheetName = "sheet1";

        // Đọc dữ liệu từ file Excel
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);

        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Lặp qua từng bản ghi trong dữ liệu Excel
        for (Map<String, String> rowData : excelData) {
            // Truy cập URL
            driver.get("https://saucelabs.com/request-demo");

            // Lấy dữ liệu từ từng hàng
            //Sử dụng getOrDefault để đảm bảo giá trị không bị null khi thiếu dữ liệu trong Excel.
            String email = rowData.getOrDefault("Business Email", "");
            String firstName = rowData.getOrDefault("First Name", "");
            String lastName = rowData.getOrDefault("Last Name", "");
            String company = rowData.getOrDefault("Company", "");
            String phone = rowData.getOrDefault("Phone Number", "");
            String interest = rowData.getOrDefault("Interest", "");
            String country = rowData.getOrDefault("Country", "");
            String comment = rowData.getOrDefault("Comments", "");

            // Thực hiện nhập liệu trên trang
            RequestDemoPageUI requestDemoPageUI = new RequestDemoPageUI(driver);

            inputText(requestDemoPageUI.findTexBoxBusinessEmail(), email);
            //cần thêm wait có điều kien
            inputText(requestDemoPageUI.findTexBoxFirstName(), firstName);
            inputText(requestDemoPageUI.findTexBoxLastName(), lastName);
            inputText(requestDemoPageUI.findTexBoxCompany(), company);
            inputText(requestDemoPageUI.findTexBoxPhone(), phone);
            selectDropdownByVisibleText(requestDemoPageUI.findTexBoxCountry(), country);
            selectDropdownByVisibleText(requestDemoPageUI.findTexBoxInterest(), interest);
            inputText(requestDemoPageUI.findTexBoxComment(), comment);
            requestDemoPageUI.findCheckBox().click();
            requestDemoPageUI.findButtonLetsTalk().click();

            System.out.println("Data submitted successfully for: " + email);
        }
        driver.quit();
    }

    // Phương thức nhập liệu vào textbox
    private static void inputText(WebElement element, String text) {
        if (element != null && text != null) {
            element.clear();
            element.sendKeys(text);
        }
    }

    // Phương thức chọn giá trị trong dropdown
    private static void selectDropdownByVisibleText(WebElement element, String text) {
        if (element != null && text != null) {
            Select select = new Select(element);
            select.selectByVisibleText(text);
        }
    }

}
