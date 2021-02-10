package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    private static final String URL = "https://otus.ru";
    private By auth = By.cssSelector("button[data-modal-id='new-log-reg']");
    private By login = By.cssSelector("input[type='text']");
    private By pass = By.cssSelector("input[type='password']");
    private By submit = By.xpath("//*[contains(text(),'Войти')]");
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get(URL);
        return this;
    }

    public void authorize() {
        driver.findElement(auth).click();
    }

    public void fillAuthForm(String userName, String password) {
        driver.findElement(login).sendKeys(userName);
        driver.findElement(pass).sendKeys(password);
        driver.findElement(submit).click();
    }

}
