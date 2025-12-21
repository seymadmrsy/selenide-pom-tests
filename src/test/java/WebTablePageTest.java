package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.WebTablesPage;

import static com.codeborne.selenide.Selenide.open;

public class WebTablePageTest {

    WebTablesPage webTablesPage;

    @BeforeMethod
    public void setUp() {

        // Selenide ayarları
        Configuration.browser = "chrome";
        Configuration.browserSize = "1536x864";
        Configuration.timeout = 10000;
        open("https://demoqa.com/webtables");

        // Page Object oluştur
        webTablesPage = new WebTablesPage();
    }

    @Test
    public void addAndEditRecordTest() {

        String email = "seymadmrsy@gmail.com";

        // Add Record
        webTablesPage.clickAdd();
        webTablesPage.formDoldur(
                "Şeyma",
                "DEMİRSOY",
                email,
                "29",
                "35000",
                "Test Uzmanı"
        );

        // Edit Record
        webTablesPage.clickEditByEmail(email);
        webTablesPage.updateSalaryAndDepartment(
                "50000",
                "QA Tester"
        );

        // Doğrulama
        webTablesPage.verifyUpdatedValues(
                email,
                "50000",
                "QA Tester"
        );
    }
}
