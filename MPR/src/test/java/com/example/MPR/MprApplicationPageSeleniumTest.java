package com.example.MPR;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class MprApplicationPageSeleniumTest {
    WebDriver driver;
    @FindBy(id = "addCarButton")
    WebElement addCarButton;
    @FindBy(id = "editCarButton")
    WebElement editCarButton;
    @FindBy(id = "deleteCarButton")
    WebElement deleteCarButton;
    @FindBy(id = "brandInput")
    WebElement brandInput;
    @FindBy(id = "modelInput")
    WebElement modelInput;
    @FindBy(id = "prodYearInput")
    WebElement prodYearInput;
    @FindBy(id = "addCarSubmit")
    WebElement addCarSubmit;
    @FindBy(id = "editCarSubmit")
    WebElement editCarSubmit;

    public static final String URL = "http://localhost:8081/index";

    public MprApplicationPageSeleniumTest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }
    public void close(){
        driver.close();
    }
    public void clickAddCarButton() {
        addCarButton.click();
    }
    public void clickEditCarButton() {
        editCarButton.click();
    }
    public void clickDeleteCarButton() {
        deleteCarButton.click();
    }
    public void fillInCarForm() {
        brandInput.sendKeys("Test Brand");
        modelInput.sendKeys("Test Model");
        prodYearInput.sendKeys("2024");
    }
    public void clearCarForm() {
        brandInput.clear();
        modelInput.clear();
        prodYearInput.clear();
    }
    public void clickAddCarSubmit() {
        addCarSubmit.click();
    }
    public void clickEditCarSubmit() {
        editCarSubmit.click();
    }
    public void addCarMetchod() {
        open();
        String[] brandNamesTable = {"Audi", "Skoda", "VW", "BMW", "Mercedes"};
        String[][] modelNamesTables = {
                {"A3", "A4"},
                {"Octavia", "Fabia"},
                {"Passat", "Golf"},
                {"Seria 3", "Seria 5"},
                {"Klasa A", "Klasa S"}
        };
        int j = 0;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            clickAddCarButton();
            brandInput.sendKeys(brandNamesTable[i]);

            modelInput.sendKeys(modelNamesTables[i][j]);
            if (j == 0) {
                j++;
                i--;
            } else j = 0;
            prodYearInput.sendKeys(String.valueOf(random.nextInt((2024 - 1990) + 1) + 1990));
            clickAddCarSubmit();
        }
    }
}
