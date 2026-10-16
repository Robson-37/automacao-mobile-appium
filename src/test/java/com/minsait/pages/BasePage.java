package com.minsait.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.minsait.driver.DriverManager; 
import com.minsait.utils.TestListener; 
import org.testng.annotations.AfterMethod; 
import org.testng.annotations.BeforeMethod; 
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners({TestListener.class})
public abstract class BasePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod public void setUp() { 
        driver = DriverManager.getDriver(); 
    }

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void clicar(WebElement elemento) {
        wait.until(ExpectedConditions.elementToBeClickable(elemento)).click();
    }

    protected void escrever(WebElement elemento, String texto) {
        wait.until(ExpectedConditions.visibilityOf(elemento));
        elemento.clear();
        elemento.sendKeys(texto);
    }

    protected String obterTexto(WebElement elemento) {
        return wait.until(ExpectedConditions.visibilityOf(elemento)).getText();
    }

    public void voltar() {
        driver.navigate().back();
    }

    public boolean isElementoVisivel(WebElement elemento) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(elemento)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    @AfterMethod public void tearDown() { 
        DriverManager.quitDriver(); 
    }
}
