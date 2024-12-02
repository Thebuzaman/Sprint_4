import org.example.pageobjects.HomePageScooter;
import org.example.pageobjects.OrderPageAboutRent;
import org.example.pageobjects.OrderPageForWhom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class MakeOrderTest {

    WebDriver driver;
    private final String orderButton;
    private final String firstname;
    private final String secondname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String data;
    private final String days;
    private final String color;
    private final String comment;


    public MakeOrderTest(String orderButton, String firstname, String secondname, String address, String metro, String phone, String data, String days, String color, String comment) {
        this.orderButton = orderButton;
        this.firstname = firstname;
        this.secondname = secondname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.data = data;
        this.days = days;
        this.color = color;
        this.comment = comment;


    }
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                // Верхняя кнопка Заказать
                {"Button_Button__ra12g", "Влад", "Пешков", "Перово поле", "Перово", "89991112233", "12.12.2024", "сутки", "black", "Комментарий"},
                // Центральная кнопка заказать
                {"Button_Button__ra12g Button_Middle__1CSJM", "Дмитрий", "Жуков", "Ленинградский вокзал", "Комсомольская", "89991112244", "13.12.2024", "двое суток", "grey", "Комментарий"},
        };
    }
    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        driver=new ChromeDriver();
        //driver=new FirefoxDriver();
    }

    @Test
    public void checkOrderTopButtonClick() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePageScooter objHomePage = new HomePageScooter(driver);

        String locatorOrderButton = String.format(".//button[@class='%s']", this.orderButton);
        objHomePage.clickOrderButton(By.xpath(locatorOrderButton));
        objHomePage.hideCookie();

        OrderPageForWhom objForWhom = new OrderPageForWhom(driver);
        objForWhom.setFormForWhom(firstname, secondname, address, metro, phone);

        OrderPageAboutRent objAboutRent = new OrderPageAboutRent(driver);
        objAboutRent.setFormAboutRent (data, days, color, comment);
        objAboutRent.clickYesOrderButton();
        assertTrue(objAboutRent.checkOrderIsConfirm());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
