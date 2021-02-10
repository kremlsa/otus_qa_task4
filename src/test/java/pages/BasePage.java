package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    //Объявление веб-драйвера
    public static WebDriver driver;

    //Конструктор базового класса
    public BasePage(WebDriver driver) {
        this.driver = driver;
        }

}
