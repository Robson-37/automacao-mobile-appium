package com.minsait.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    // === Elementos Mapeados via XPath no Catálogo ===
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ImageView")
    private WebElement primeiroElemento;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ImageView")
    private WebElement segundoElemento;

    // === Elementos da Funcionalidade de Login (My Demo App RN) ===
    @AndroidFindBy(accessibility = "open menu")
    private WebElement botaoMenu;

    @AndroidFindBy(accessibility = "menu item log in")
    private WebElement itemMenuLogin;

    @AndroidFindBy(accessibility = "Username input field")
    private WebElement campoUsuario;

    @AndroidFindBy(accessibility = "Password input field")
    private WebElement campoSenha;

    @AndroidFindBy(accessibility = "Login button")
    private WebElement botaoLogin;

    @AndroidFindBy(xpath = "//*[contains(@text, 'is required') or contains(@text, 'Provided credentials')] | //android.view.ViewGroup[@content-desc='generic-error-message']//android.widget.TextView")
    private WebElement mensagemErro;

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    // === Métodos de Interação com os Elementos Mapeados ===
    public LoginPage clicarPrimeiroElemento() {
        clicar(primeiroElemento);
        return this;
    }

    public LoginPage voltarParaCatalogo() {
        voltar();
        return this;
    }

    public LoginPage clicarSegundoElemento() {
        clicar(segundoElemento);
        return this;
    }

    public boolean isPrimeiroElementoVisivel() {
        return isElementoVisivel(primeiroElemento);
    }

    public boolean isSegundoElementoVisivel() {
        return isElementoVisivel(segundoElemento);
    }

    // === Métodos do Fluxo de Login ===
    public LoginPage abrirMenu() {
        clicar(botaoMenu);
        return this;
    }

    public LoginPage clicarMenuLogin() {
        clicar(itemMenuLogin);
        return this;
    }

    public LoginPage preencherUsuario(String usuario) {
        escrever(campoUsuario, usuario);
        return this;
    }

    public LoginPage preencherSenha(String senha) {
        escrever(campoSenha, senha);
        return this;
    }

    public LoginPage clicarBotaoLogin() {
        clicar(botaoLogin);
        return this;
    }

    public LoginPage realizarLogin(String usuario, String senha) {
        preencherUsuario(usuario);
        preencherSenha(senha);
        clicarBotaoLogin();
        return this;
    }

    public String obterMensagemErro() {
        return obterTexto(mensagemErro);
    }
}