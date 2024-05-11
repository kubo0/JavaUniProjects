package com.example.MPR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MprApplicationSeleniumTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void addCars() {
        MprApplicationPageSeleniumTest page = new MprApplicationPageSeleniumTest(driver);
        page.addCarMetchod();
        page.close();
    }

    @Test
    public void addCarTest() {
        MprApplicationPageSeleniumTest page = new MprApplicationPageSeleniumTest(driver);
        page.open();
        page.clickAddCarButton();
        page.fillInCarForm();
        page.clickAddCarSubmit();
    }

    @Test
    public void editCarTest() {
        MprApplicationPageSeleniumTest page = new MprApplicationPageSeleniumTest(driver);
        page.open();
        page.clickEditCarButton();
        page.clearCarForm();
        page.fillInCarForm();
        page.clickEditCarSubmit();
    }

    @Test
    public void deleteCarTest() {
        MprApplicationPageSeleniumTest page = new MprApplicationPageSeleniumTest(driver);
        page.open();
        page.clickDeleteCarButton();
    }
}
