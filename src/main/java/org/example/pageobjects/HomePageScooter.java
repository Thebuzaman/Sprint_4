package org.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

public class HomePageScooter {

    private WebDriver driver;
    // Кнопка принятия Cookie
    private By cookieButton = By.xpath(".//button[text()='да все привыкли']");
    // Кнопка Заказать на главной странице
    private By buttonOrder = By.xpath(".//*[text()='Заказать']");
    // Локатор вопроса-ответа
    private By item = By.className("accordion__item");
    // Локатор вопроса
    private By heading = By.className("accordion__heading");
    // Локатор ответа
    private By panel = By.className("accordion__panel");
    // Заготовка локатора для определения что поле вопроса раскрылось и виден ответ
    private String answerIsVisible = ".//div[@id='accordion__heading-%d']";

    public HomePageScooter (WebDriver driver) {
        this.driver = driver;
    }
    // Метод для принятия Cookie
    public void hideCookie() {
        driver.findElement(cookieButton).click();
    }
    // Метод для нажатия на кнопку Заказать
    public void clickOrderButton(int index) {
        List<WebElement> buttonList = driver.findElements(buttonOrder);
        WebElement element = buttonList.get(index);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    // Метод для нажатия на поле с вопросом
    public WebElement clickListItem(int index) {
        List<WebElement> itemList = driver.findElements(item);
        WebElement elementItem = itemList.get(index);
        WebElement elementHeading = elementItem.findElement(heading);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elementHeading);
        // Ожидаем появления элемента
        new WebDriverWait(driver, 3).until(driver -> (elementHeading.isEnabled()));
        elementHeading.click();
        // Проверяем, что вкладка вопроса раскрылась и появился ответ. Атрибут "aria-expanded" меняет значение с false на true
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.attributeToBe(By.xpath(String.format(answerIsVisible, index)), "aria-expanded", "true"));
        return elementItem;
    }
    // Метод возвращающий ответ
    public String textByLocator(WebElement element) {
        return element.findElement(panel).getText();
    }
}
