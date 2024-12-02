import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageobjects.HomePageScooter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
//import org.openqa.selenium.firefox.FirefoxDriver;


@RunWith(Parameterized.class)
public class GeneralQuestionsTest {
    WebDriver driver;

    private final String question;
    private final String answer;
    private final String text;

    public GeneralQuestionsTest(String question, String answer, String text) {
        this.answer = answer;
        this.question = question;
        this.text = text;
    }
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"accordion__heading-0", "accordion__panel-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1", "accordion__panel-1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2", "accordion__panel-2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3", "accordion__panel-3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4", "accordion__panel-4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"accordion__heading-5", "accordion__panel-5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6", "accordion__panel-6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"accordion__heading-7", "accordion__panel-7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }
    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
       // WebDriverManager.firefoxdriver().setup();
        driver=new ChromeDriver();
       // driver=new FirefoxDriver();

    }

    @Test
    public void checkListItemClick() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePageScooter objHomePage = new HomePageScooter(driver);
        objHomePage.hideCookie();

        objHomePage.clickListItem(this.question);
        String textAnswer = objHomePage.textByLocator(this.answer);
        Assert.assertEquals(this.text, textAnswer);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
