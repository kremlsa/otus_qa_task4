package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    private static final String URL = "https://otus.ru";
    private final By auth = By.cssSelector("button[data-modal-id='new-log-reg']");
    private final By login = By.cssSelector("input[type='text']");
    private final By pass = By.cssSelector("input[type='password']");
    private final By submit = By.xpath("//*[contains(text(),'Войти')]");
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get(URL);
        return this;
    }

    public MainPage auth() {
        driver.findElement(auth).click();
        return this;
    }

    public void fillAuthForm(String userName, String password) {
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(submit));
        driver.findElement(login).sendKeys(userName);
        driver.findElement(pass).sendKeys(password);
        driver.findElement(submit).click();
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='email']")));
    }

}
