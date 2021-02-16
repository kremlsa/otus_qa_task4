package testcases;

import factory.BrowserName;
import factory.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    protected final Logger logger = LogManager.getLogger();



    @Before
    public void Setup() {

        //Получаем имя браузера из параметра -Dbrowser командной строки, если не указан то по умолчанию chrome
        String name = Optional.ofNullable(System.getProperty("browser")).orElse("chrome");

        //Получаем имя драйвера из класса Enum
        BrowserName browserName = BrowserName.findByName(name);

        //Если имя браузера не было распознано корректно, то логируем предупреждение
        if (browserName == BrowserName.DEFAULT) {
            logger.warn("WebDriver name from the cmdline is not recognized %" + name
                    + "% use Firefox");
        }

        //Создаём вебдрайвер через статический метод класса WebDriverFactory
        driver = WebDriverFactory.create(browserName);
        //Устанавливаем максимальный размер окна для браузера
        driver.manage().window().maximize();
        logger.info("Start WebDriver " + browserName.getBrowserName());

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.close();
            logger.info("Shutdown WebDriver");
        } else {
            logger.error("Error WebDriver not found");
        }
    }

    public void openPage(String url) {
        driver.get(url);
    }

    //Вспомогательный метод для клика по элементу
    public void clickByElement(By selector) {
        driver.findElement(selector).click();
    }

}
