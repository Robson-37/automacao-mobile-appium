package com.minsait.tests;

import com.minsait.pages.LoginPage;
import com.minsait.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Mobile Automation")
@Feature("Catálogo e Autenticação")
public class LoginTest extends BaseTest {

    @Test(description = "Deve interagir com os elementos mapeados via XPath no catálogo")
    @Story("Navegação no Catálogo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida a interação de clique nos dois primeiros produtos do catálogo retornando à listagem principal.")
    public void deveClicarNosElementosMapeados() {
        LoginPage loginPage = new LoginPage(driver);

        // Passo 1: Valida e clica no primeiro item
        Allure.step("Validar visibilidade e clicar no primeiro produto do catálogo", () -> {
            Assert.assertTrue(loginPage.isPrimeiroElementoVisivel(),
                    "O primeiro item deve estar visível no catálogo");
            loginPage.clicarPrimeiroElemento();
            ScreenshotUtil.capturarEvidencia("Primeiro produto clicado");
        });

        // Passo 2: Retorna ao catálogo
        Allure.step("Retornar ao catálogo principal", () -> {
            loginPage.voltarParaCatalogo();
            ScreenshotUtil.capturarEvidencia("Catálogo após retorno");
        });

        // Passo 3: Valida e clica no segundo item
        Allure.step("Validar visibilidade e clicar no segundo produto do catálogo", () -> {
            Assert.assertTrue(loginPage.isSegundoElementoVisivel(),
                    "O segundo item deve estar visível após retornar ao catálogo");
            loginPage.clicarSegundoElemento();
            ScreenshotUtil.capturarEvidencia("Segundo produto clicado");
        });
    }

    @Test(description = "Deve navegar até a tela de login pelo menu lateral e validar campos obrigatórios")
    @Story("Validação de Campos Obrigatórios")
    @Severity(SeverityLevel.NORMAL)
    @Description("Acessa a tela de login pelo menu lateral e valida que o sistema bloqueia envio sem credenciais exibindo mensagem de erro.")
    public void deveValidarCamposObrigatoriosDeLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Passo 1: Abrir menu e acessar login
        Allure.step("Abrir menu lateral e acessar tela de login", () -> {
            loginPage.abrirMenu().clicarMenuLogin();
            ScreenshotUtil.capturarEvidencia("Tela de login aberta");
        });

        // Passo 2: Clicar sem preencher e validar erro
        Allure.step("Submeter login sem credenciais e validar mensagem de erro", () -> {
            loginPage.clicarBotaoLogin();
            String erro = loginPage.obterMensagemErro();
            ScreenshotUtil.capturarEvidencia("Mensagem de erro exibida");
            Assert.assertNotNull(erro, "Uma mensagem de erro deveria ser exibida ao tentar logar sem dados");
            Assert.assertFalse(erro.trim().isEmpty(), "A mensagem de erro não pode ser vazia");
        });
    }
}
