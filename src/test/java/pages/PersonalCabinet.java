package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import users.UserProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalCabinet extends BasePage {

    private static final String URL = "https://otus.ru/lk/biography/personal/";
    private Map<By, String> fields = new HashMap<>();
    private UserProfile user = new UserProfile().loadFromJson("users.json");

    //selectors
    By fname = By.cssSelector("input[name='fname']");
    By fnameLatin = By.cssSelector("input[name='fname_latin']");
    By lname = By.cssSelector("input[name='lname']");
    By lnameLatin = By.cssSelector("input[name='lname_latin']");
    By blogName = By.cssSelector("input[name='blog_name']");
    By company = By.cssSelector("input[name='company']");
    By work = By.cssSelector("input[name='work']");
    By deleteButton = By.cssSelector(".container__col_md-0 .js-formset-delete");
    By saveButton = By.cssSelector("button[name*='continue']");
    By country = By.xpath("//*[@data-ajax-slave='/lk/biography/cv/lookup/cities/by_country/']");
    By city = By.cssSelector(".js-lk-cv-dependent-slave-city");
    By language = By.xpath("//*[contains(text(),'Уровень')]//..//..//div//div");
    By contact1 = By.cssSelector("#id_contact-0-value");
    By contact2 = By.cssSelector("#id_contact-1-value");
    By contact1menu = By.xpath("//div[@data-num='0']//*[contains(text(),'Способ')]//..//..//..");
    By contact2menu = By.xpath("//div[@data-num='1']//*[contains(text(),'Способ')]//..//..//..");
    By contact1type = By.xpath("//div[@data-num='0']//*[contains(text(),'Skype')]");
    By contact2type = By.xpath("//div[@data-num='1']//*[contains(text(),'Skype')]");
    By add = By.xpath("//button[contains(text(),'Добавить')]");

    //Конструктор
    public PersonalCabinet (WebDriver driver) {
        super(driver);
        initialize();
    }


    //Инициализируем коллекцию со значениями полей
    public void initialize() {
        fields.put(fname, user.getFname());
        fields.put(fnameLatin, user.getFnameLatin());
        fields.put(lname, user.getLname());
        fields.put(lnameLatin, user.getLnameLatin());
        fields.put(blogName, user.getBlogName());
        fields.put(company, user.getCompany());
        fields.put(work, user.getWork());
    }

    //Получить экземпляр страницы
    public PersonalCabinet open() {
        driver.get(URL);
        return this;
    }

    //Вспомогательный метод для очистки полей
    public PersonalCabinet clearFields() {
        for (Map.Entry<By, String> entry : fields.entrySet()) {
            driver.findElement(entry.getKey()).clear();
        }
        return this;
    }

    //Вспомогательный метод для очистки контактов
    public void clearContacts() {
        List<WebElement> deleteButtons = new ArrayList<>();
        deleteButtons = driver.findElements(deleteButton);
        deleteButtons.forEach(x->x.click());

        //Сохраняем
        waitUntilClickable(saveButton);
    }

    //Тесты иногда глючат, боремся с - element click intercepted:
    public void waitUntilClickable(By selector) {
        WebElement element = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(selector));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    public void fillFields() {

        //Заполняем поля из колллекции
        for (Map.Entry<By, String> entry : fields.entrySet()) {
            driver.findElement(entry.getKey()).sendKeys(entry.getValue());
        }

        //Выбираем страну
        waitUntilClickable(country);
        waitUntilClickable(By.cssSelector("button[title='" + user.getCountry() + "']"));

        //Выбираем город
        waitUntilClickable(city);
        waitUntilClickable(By.cssSelector("button[title='" + user.getCity() + "']"));

        //Выбираем уровень знания английского
        waitUntilClickable(language);
        waitUntilClickable(By.cssSelector("button[title*='" + user.getEnglish() + "']"));


        //Добавляем первый контакт
        driver.findElement(contact1).sendKeys(user.getContact1());
        waitUntilClickable(contact1menu);
        waitUntilClickable(contact1type);
        waitUntilClickable(add);

        //Добавляем второй контакт
        driver.findElement(contact2).sendKeys(user.getContact2());
        waitUntilClickable(contact2menu);
        waitUntilClickable(contact2type);
        waitUntilClickable(add);

        //Сохраняем
        waitUntilClickable(saveButton);
    }

    //Тест проверяем правильность заполнения контактов
    public void checkContacts() {
        Assert.assertTrue(driver.findElement(contact1)
                .getAttribute("value")
                .contains(user.getContact1()));
        Assert.assertTrue(driver.findElement(contact2)
                .getAttribute("value")
                .contains(user.getContact2()));
    }

}
