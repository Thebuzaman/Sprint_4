package org.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePageScooter {

    private WebDriver driver;
    // Кнопка принятия Cookie
    private By cookieButton = By.xpath(".//button[text()='да все привыкли']");

    public HomePageScooter (WebDriver driver) {
        this.driver = driver;
    }
    // Метод для принятия Cookie
    public void hideCookie() {
        driver.findElement(cookieButton).click();
    }
    // Метод для нажатия на кнопку Заказать
    public void clickOrderButton(By locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
        driver.findElement(locator).click();
    }
    // Метод для нажатия на поле с вопросом
    public void clickListItem(String locator) {
      By locatorQuestion = By.xpath(String.format(".//div[@id='%s']", locator));
      ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(locatorQuestion));
      driver.findElement(locatorQuestion).click();
      // Ждем раскрытия поля с ответом
      new WebDriverWait(driver, 3)
              .until(ExpectedConditions.attributeToBe(locatorQuestion, "aria-expanded", "true"));
    }
    // Метод возвращающий ответ
    public String textByLocator(String locator) {
      By locatorAnswer = By.xpath(String.format(".//div[@id='%s']/p", locator));
      return driver.findElement(locatorAnswer).getText();
    }
}
