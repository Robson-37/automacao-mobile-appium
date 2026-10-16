package com.minsait.utils;

import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Allure.addAttachment("Log - Início do Teste",
                "text/plain",
                "Iniciando: " + result.getMethod().getMethodName()
                        + "\nClasse: " + result.getTestClass().getName()
                        + "\nDescrição: " + result.getMethod().getDescription()
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.addAttachment("Log - Resultado",
                "text/plain",
                "✅ SUCESSO: " + result.getMethod().getMethodName()
                        + "\nTempo de execução: " + (result.getEndMillis() - result.getStartMillis()) + "ms"
        );
        ScreenshotUtil.capturarEvidencia("Tela Final - SUCESSO: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.addAttachment("Log - Resultado",
                "text/plain",
                "❌ FALHA: " + result.getMethod().getMethodName()
                        + "\nMotivo: " + result.getThrowable().getMessage()
                        + "\nTempo de execução: " + (result.getEndMillis() - result.getStartMillis()) + "ms"
        );
        ScreenshotUtil.capturarEvidencia("Tela Final - FALHA: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.addAttachment("Log - Resultado",
                "text/plain",
                "⚠️ PULADO: " + result.getMethod().getMethodName()
        );
    }
}