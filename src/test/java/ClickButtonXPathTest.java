import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClickButtonXPathTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/elements");
    }

    @Test
    public void clickButtonTest() throws InterruptedException {
        // XPath ile Buttons bulundu
        WebElement buttonClick = driver.findElement(By.xpath("//*[contains(text(),'Buttons')]"));
        System.out.println("Bulunan değer : " + buttonClick.getText());
        buttonClick.click();

        // Buton görünür hale getirildi
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", buttonClick);

        // "Click Me" butonu bulundu ve tıklandı
        WebElement clickMe = driver.findElement(By.xpath("//div[contains(@class,'mt-4')]//button[normalize-space()='Click Me']"));
        System.out.println("Bulunan buton : " + clickMe.getText());
        clickMe.click();
        Thread.sleep(2000);  // daha kısa süre yeterli olabilir

        // Doğrulama mesajı kontrolü
        WebElement dogruMu = driver.findElement(By.xpath("//*[@id='dynamicClickMessage']"));
        String actualMessage = dogruMu.getText();
        String expectedMessage = "You have done a dynamic click";
        System.out.println("Mesaj : " + actualMessage);

        // Assertion
        Assert.assertEquals(actualMessage, expectedMessage, "Mesaj beklenen şekilde gelmedi!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
