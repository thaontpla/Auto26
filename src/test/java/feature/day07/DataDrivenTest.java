package feature.day07;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.LoginPageUI;
import untils.ExcelUtils;

import java.util.List;
import java.util.Map;

public class DataDrivenTest {
    public WebDriver driver;
    public LoginPageUI loginPageUI;
    String excelFilePath = "dataLogin.xlsx";
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPageUI = new LoginPageUI(driver);
    }
    public void inputData(String user,String pass){
        loginPageUI.inputUserName().sendKeys(user);
        loginPageUI.inputPassWord().sendKeys(pass);
    }
    public void clickLogin(){
        loginPageUI.buttonLogin().click();
    }
    @Test
    public void loginSuccessful() {
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, "Sheet1");
        for (Map<String, String> rowData : excelData) {
            driver.get("https://www.saucedemo.com/");
            inputData(rowData.get("Username"), rowData.get("Password"));
            clickLogin();
            break;
        }
    }

    @Test
    public void loginFailed() {
        int count = 0;
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, "Sheet1");
        for (Map<String, String> rowData : excelData) {
            if (count > 0) {
                driver.get("https://www.saucedemo.com/");
                inputData(rowData.get("Username"), rowData.get("Password"));
                clickLogin();
            }
            count++;
        }

        WebElement error = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(error.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
