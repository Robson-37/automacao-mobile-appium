package com.minsait.utils;

import com.minsait.driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class ScreenshotUtil {

    /**
     * Captura a tela do dispositivo Android (emulador ou device físico)
     * via Appium e anexa como evidência PNG no Allure Report.
     *
     * @param descricao Nome da evidência que aparecerá no relatório Allure
     */
    public static void capturarEvidencia(String descricao) {
        try {
            AndroidDriver driver = DriverManager.getDriver();
            if (driver == null) {
                System.err.println("[ScreenshotUtil] Driver não disponível para captura: " + descricao);
                return;
            }
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(descricao, "image/png", new ByteArrayInputStream(bytes), ".png");
        } catch (Exception e) {
            System.err.println("[ScreenshotUtil] Erro ao capturar print do dispositivo: " + e.getMessage());
        }
    }
}
