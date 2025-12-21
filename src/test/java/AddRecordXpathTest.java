import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddRecordXpathTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/webtables");
    }

    @Test
    public void addAndUpdateRecordTest() throws InterruptedException {

        // Add butonu bulunup tıklanıyor (XPath ile)
        WebElement add = driver.findElement(By.xpath("//*[@id='addNewRecordButton']"));
        System.out.println("XPath ile bulunan Add butonu: " + add.getText());
        add.click();

        // Form XPath ile dolduruluyor
        driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys("Şeyma");
        bekle(1);
        driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys("DEMİRSOY");
        bekle(1);
        driver.findElement(By.xpath("//*[@id='userEmail']")).sendKeys("seymadmrsy@gmail.com");
        bekle(1);
        driver.findElement(By.xpath("//*[@id='age']")).sendKeys("29");
        bekle(1);
        driver.findElement(By.xpath("//*[@id='salary']")).sendKeys("35000");
        bekle(1);
        driver.findElement(By.xpath("//*[@id='department']")).sendKeys("Test Uzmanı");
        bekle(1);
        driver.findElement(By.xpath("//*[@id='submit']")).click();
        bekle(2);

        // Edit butonuna tıklanıyor
        WebElement editBtn = driver.findElement(By.xpath("//*[@id='edit-record-4']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", editBtn);
        editBtn.click();

        // Alanlar güncelleniyor
        WebElement salaryBox = driver.findElement(By.xpath("//*[@id='salary']"));
        salaryBox.clear();
        salaryBox.sendKeys("50000");
        bekle(1);

        WebElement departmentBox = driver.findElement(By.xpath("//*[@id='department']"));
        departmentBox.clear();
        departmentBox.sendKeys("QA Tester");
        bekle(1);

        driver.findElement(By.xpath("//*[@id='submit']")).click();
        bekle(2);

        // XPath ile güncellenen alanlar kontrol ediliyor
        WebElement updateKontrolSalary =
                driver.findElement(By.xpath("(//div[@class='rt-tr-group'])[4]//div[@class='rt-td'][5]"));
        WebElement updateKontrolDepartment =
                driver.findElement(By.xpath("(//div[@class='rt-tr-group'])[4]//div[@class='rt-td'][6]"));

        String actualSalary = updateKontrolSalary.getText();
        String actualDepartment = updateKontrolDepartment.getText();

        System.out.println("Güncellenen Salary: " + actualSalary);
        System.out.println("Güncellenen Department: " + actualDepartment);

        // Doğrulamalar
        Assert.assertEquals(actualSalary, "50000", "Güncellenen salary yanlış!");
        Assert.assertEquals(actualDepartment, "QA Tester", "Güncellenen department yanlış!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public static void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Bekleme hatası: " + e.getMessage());
        }
    }
}
