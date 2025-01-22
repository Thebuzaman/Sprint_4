import org.example.pageobjects.HomePageScooter;
import org.example.pageobjects.OrderPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MakeOrderTest extends setupTest {

    private final int orderButton;
    private final String firstname;
    private final String secondname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String data;
    private final String days;
    private final String color;
    private final String comment;

    public MakeOrderTest(int orderButton, String firstname, String secondname, String address, String metro, String phone, String data, String days, String color, String comment) {
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
                {0, "Влад", "Пешков", "Перово поле", "Перово", "89991112233", "12.12.2024", "сутки", "black", "Комментарий"},
                // Центральная кнопка заказать
                {1, "Дмитрий", "Жуков", "Ленинградский вокзал", "Комсомольская", "89991112244", "13.12.2024", "двое суток", "grey", "Комментарий"},
        };
    }
    @Test
    public void checkOrderButtonClickAndFillFormGetPositiveResult() {
        HomePageScooter objHomePage = new HomePageScooter(driver);
        objHomePage.hideCookie();

        objHomePage.clickOrderButton(this.orderButton);

        OrderPage objOrder = new OrderPage(driver);
        // Форма для кого самокат
        objOrder.setFormForWhom(firstname, secondname, address, metro, phone);
        // Форма Про аренду
        objOrder.setFormAboutRent (data, days, color, comment);
        objOrder.clickYesOrderButton();
        assertTrue(objOrder.checkOrderIsConfirm());
    }
}

