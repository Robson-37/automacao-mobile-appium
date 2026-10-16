package com.minsait.tests;

import com.minsait.driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}