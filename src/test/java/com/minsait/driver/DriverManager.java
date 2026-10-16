package com.minsait.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import java.time.Duration;

public class DriverManager {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            try {
                String appPath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "app", "Android-MyDemoAppRN.1.3.0.build-244.apk")
                        .toAbsolutePath()
                        .toString();

                File appFile = new File(appPath);
                if (!appFile.exists()) {
                    throw new IllegalStateException("APK não encontrado no caminho: " + appPath);
                }

                UiAutomator2Options options = new UiAutomator2Options();
                options.setPlatformName("Android");
                options.setAutomationName("UiAutomator2");
                options.setDeviceName("emulator-5554");
                options.setApp(appPath);
                options.setAutoGrantPermissions(true);
                options.setNewCommandTimeout(Duration.ofSeconds(60));

                driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
            } catch (Exception e) {
                throw new RuntimeException("Falha ao inicializar o driver Appium: " + e.getMessage(), e);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Erro ao finalizar a sessão do driver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}


