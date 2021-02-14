package testcases;

import org.junit.Before;
import org.junit.Test;
import pages.MainPage;
import pages.PersonalCabinet;

import java.util.Optional;

public class Task4Test extends BaseTest{
    String login;
    String pass;

    @Before
    //Перед началом теста очищаем форму от старых контактов
    public void clearForm() {
        //Получаем учётные данные
        setCreds();

        //Инициализируем страницы
        MainPage main = new MainPage(driver);
        PersonalCabinet cabinet = new PersonalCabinet(driver);
        logger.info("Clear form before test");

        //Аутентифицируемся
        main.open()
                .auth()
                .fillAuthForm(login, pass);

        //Переходим в личный кабинет, очищаем форму
        cabinet.open()
            .clearContacts();

        //Сбрасываем сессию
        driver.manage().deleteAllCookies();

    }

    @Test
    public void otusTest() {
        //Получаем учётные данные
        setCreds();

        //Инициализируем страницы
        MainPage main = new MainPage(driver);
        PersonalCabinet cabinet = new PersonalCabinet(driver);
        logger.info("Run Test for Otus");

        //Аутентифицируемся
        main.open()
                .auth()
                .fillAuthForm(login, pass);

        //Переходим в личный кабинет, заполняем форму
        logger.info("Fill form, add contacts");
        cabinet.open()
            .clearFields()
            .fillFields();

        //Сбрасываем сессию
        driver.manage().deleteAllCookies();

        //Аутентифицируемся
        main.open()
                .auth()
                .fillAuthForm(login, pass);

        //Тест, переходим в личный кабинет, проверяем добавленные контакты
        logger.info("Check added contacts");
        cabinet.open()
            .checkContacts();

        //Тест пройден
        logger.info("Test is passed");
    }

    //Вспомогательный метод, для получения учётных данных из переменных среды окружения
    public void setCreds() {
        //Получаем имя пользователя из параметра -Dlogin командной строки
        login = Optional.ofNullable(System.getProperty("login")).orElse("null");

        //Получаем пароль из параметра -Dpassword командной строки
        pass = Optional.ofNullable(System.getProperty("password")).orElse("null");
    }

}
