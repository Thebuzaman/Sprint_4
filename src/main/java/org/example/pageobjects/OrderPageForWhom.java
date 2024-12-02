package org.example.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPageForWhom {
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
    // private By nextButton = By.xpath(".//button[text() = 'Далее']");


    public OrderPageForWhom(WebDriver driver){
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
        driver.findElement(By.xpath(String.format("//*[text()='%s']",metro))).click();
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
}
