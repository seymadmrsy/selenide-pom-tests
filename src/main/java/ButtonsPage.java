
package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

public class ButtonsPage {
    //Buttons menüye tıklar
    SelenideElement buttonsClick = $("div ul li:nth-child(5)");

    //click me butonuna tıkla
    SelenideElement clickMeButton =$("div.mt-4:nth-child(4) button");

    //tıklama sonrası çıkan mesaj
    SelenideElement clickMesaj = $("#dynamicClickMessage");


    // Menülerden "Buttons" sekmesine tıkla
    public void goToButtons(){
        System.out.println("Tıklanan Değer : " + buttonsClick.text());
        buttonsClick.click();
    }

    // "Click Me" butonuna tıkla
    public void clickMe(){
        System.out.println("Tıklanan Değer : " + clickMeButton.text());
        buttonsClick.scrollIntoView(false);
        clickMeButton.click();
    }

    // Mesaj doğru şekilde geldi mi kontrol et
    public void dogruMu(){
        System.out.println("Gelen Mesaj : " + clickMesaj.text());
        clickMesaj.shouldHave(text("You have done a dynamic click"));

    }
}