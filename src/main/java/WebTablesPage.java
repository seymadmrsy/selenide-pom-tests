package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebTablesPage {

    private final SelenideElement addButton = $("#addNewRecordButton");
    private final SelenideElement firstName = $("#firstName");
    private final SelenideElement lastName = $("#lastName");
    private final SelenideElement email = $("#userEmail");
    private final SelenideElement age = $("#age");
    private final SelenideElement salary = $("#salary");
    private final SelenideElement department = $("#department");
    private final SelenideElement submit = $("#submit");

    public void clickAdd(){
        System.out.println("Bulunan Değer : " + addButton.text());
        addButton.click();
    }

    public void formDoldur(String fName,
                           String lName,
                           String mail,
                           String ageValue,
                           String salaryValue,
                           String dept){

        firstName.setValue(fName);
        lastName.setValue(lName);
        email.setValue(mail);
        age.setValue(ageValue);
        salary.setValue(salaryValue);
        department.setValue(dept);
        submit.click();
    }

    public void clickEditByEmail(String mail) {
        SelenideElement editButton = $x(
                "//div[text()='" + mail + "']/ancestor::div[@role='row']//span[@title='Edit']"
        );

        editButton.scrollIntoView(true).click();
    }

    public void updateSalaryAndDepartment(String newSalary, String newDept) {
        salary.clear();
        salary.setValue(newSalary);

        department.clear();
        department.setValue(newDept);

        submit.click();
    }

    public void verifyUpdatedValues(String mail,
                                    String expectedSalary,
                                    String expectedDept) {

        SelenideElement row = $x(
                "//div[text()='" + mail + "']/ancestor::div[@role='row']"
        );

            row.shouldHave(text(expectedSalary));
            row.shouldHave(text(expectedDept));
            System.out.println("Doğrulama Başarılı : Mail " + mail);
    }

}
