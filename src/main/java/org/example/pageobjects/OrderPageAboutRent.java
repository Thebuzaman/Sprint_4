package org.example.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPageAboutRent {
    private WebDriver driver;
    // Поле Когда привезти
    private By deliveryTimeField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле Срок аренды
    private By rentTimeField = By.xpath(".//div[@class='Dropdown-placeholder']");
    // Поле комментарий
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка Заказать
    private By buttonOrder = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    // Кнопка Да
    private By yesOrderButton = By.xpath(".//button[text()='Да']");
    // Текст на форме подтверждения заказа
    private By orderСonfirmForm = By.xpath(".//div[text()='Заказ оформлен']");


    public OrderPageAboutRent(WebDriver driver) {
        this.driver = driver;
    }
    // Метод для заполнения поля Когда привезти
    public void setDeliveryTimeField (String deliveryTime) {
        driver.findElement(deliveryTimeField).sendKeys(deliveryTime);
        // Снять фокус с календаря на шапку
        driver.findElement(By.xpath(".//div[@class='Header_Header__214zg']")).click();
    }
    // Метод для заполнения поля Срок аренды
    public void setRentTimeField (String rentTime) {
        driver.findElement(rentTimeField).click();
        driver.findElement(By.xpath(String.format("//*[text()='%s']",rentTime))).click();
    }
    // Метод для выбора Цвета самоката
    public  void  setColorField(String color) {
        driver.findElement(By.id(color)).click();
    }
    // Метод для заполнения поля Комментарии
    public  void  setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    // Метод для нажатия кнопки Заказать
    public void clickOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(buttonOrder));
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(buttonOrder));
        driver.findElement(buttonOrder).click();
    }
    // Метод для заполнения формы Про аренду
    public void setFormAboutRent (String date, String time, String color, String comment) {
        setDeliveryTimeField(date);
        setRentTimeField(time);
        setColorField(color);
        setCommentField(comment);
        clickOrderButton();
    }
    // Метод для подтверждения оформления заказа
    public void clickYesOrderButton() {
        new WebDriverWait(driver,3).until(ExpectedConditions.elementToBeClickable(yesOrderButton));
        driver.findElement(yesOrderButton).click();
    }
    // Метод для подтверждения, что заказ оформлен
    public boolean checkOrderIsConfirm() {
        return  driver.findElement(orderСonfirmForm).isDisplayed();
    }
}
