package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    //Объявление веб-драйвера
   protected WebDriver driver;

    //Конструктор базового класса
    public BasePage(WebDriver driver) {
        this.driver = driver;
        }

}
