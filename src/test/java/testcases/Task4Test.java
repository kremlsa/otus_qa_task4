package testcases;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.util.Optional;

public class Task4Test extends BaseTest{

    @Test
    public void otusTest() {

        //Получаем имя пользователя из параметра -Dlogin командной строки
        String login = Optional.ofNullable(System.getProperty("login")).orElse("null");
        logger.info(login);
        //Получаем пароль из параметра -Dpassword командной строки
        String pass = Optional.ofNullable(System.getProperty("password")).orElse("null");

        logger.info("Run Test for Otus");

        MainPage main = new MainPage(driver);

        main.open().authorize();
        main.fillAuthForm(login, pass);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Тест пройден
        logger.info("Test is passed");
    }
}
