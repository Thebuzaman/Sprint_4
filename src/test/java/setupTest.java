import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class setupTest {
    WebDriver driver;
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        driver=new ChromeDriver();
        //driver=new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
