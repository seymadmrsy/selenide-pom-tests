
import com.codeborne.selenide.Configuration;
import pages.ButtonsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static com.codeborne.selenide.Selenide.open;



public class ButtonsPageTest {

        ButtonsPage buttonsPage;

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1600x900";
        Configuration.timeout = 10000; // bekleme süresi

        open("https://demoqa.com/elements");
        buttonsPage = new ButtonsPage();
    }
    @Test
    public void clickMeButtonTest(){

        //Buttons menüsüne tıkla
        buttonsPage.goToButtons();

        //Click Me butonuna tıkla
        buttonsPage.clickMe();

        //Doğrulama mesajını assert et
        buttonsPage.dogruMu();

    }

}
