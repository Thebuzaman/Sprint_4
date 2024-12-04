package org.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class OrderPage {
    private WebDriver driver;
    // Поле Имя
    private By firstnameField = By.xpath(".//input[@placeholder='* Имя']");
    // Поле Фамилия
    private By secondnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле Адрес
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле Станция метро
    private By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    // Поле Телефон
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
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
    // Локатор шапки
    private By header = By.xpath(".//div[@class='Header_Header__214zg']");
    // Заготовка для поиска элемента в выпадающем списке
    private String listItem = "//*[text()='%s']";

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    // Метод для заполнения поля Имя
    public void setFirstnameField (String firstname) {
        driver.findElement(firstnameField).sendKeys(firstname);
    }
    // Метод для заполнения поля Фамилия
    public void setSecondnameField (String secondname) {
        driver.findElement(secondnameField).sendKeys(secondname);
    }
    // Метод для заполнения поля Адрес
    public void setAddress (String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    // Метод для выбора станции метро
    public void setMetroStation (String metro) {
        driver.findElement(metroStation).click();
        driver.findElement(By.xpath(String.format(listItem,metro))).click();
    }
    // Метод для заполнения поля Телефон
    public void setPhoneFieldField (String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }
    // Метод для нажатия на кнопку Далее
    public void clickNextButton () {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(nextButton));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }
    // Метод для заполнения формы Для кого самокат
    public void setFormForWhom(String firstname, String secondname,String address, String metro, String phone) {
        setFirstnameField (firstname);
        setSecondnameField (secondname);
        setAddress (address);
        setMetroStation (metro);
        setPhoneFieldField (phone);
        clickNextButton();
    }
    // Метод для заполнения поля Когда привезти
    public void setDeliveryTimeField (String deliveryTime) {
        driver.findElement(deliveryTimeField).sendKeys(deliveryTime);
        // Снять фокус с календаря на шапку
        driver.findElement(header).click();
    }
    // Метод для заполнения поля Срок аренды
    public void setRentTimeField (String rentTime) {
        driver.findElement(rentTimeField).click();
        driver.findElement(By.xpath(String.format(listItem,rentTime))).click();
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

