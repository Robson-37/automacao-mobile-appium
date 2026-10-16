# 📱 Ecossistema de Automação Mobile com Appium

## Visão Geral

Framework de **testes automatizados para aplicativos Android** desenvolvido em Java, utilizando Appium com UiAutomator2 para interagir com o app **My Demo App RN** (Sauce Labs). Os testes são orquestrados pelo TestNG e os resultados são publicados em um relatório visual e interativo via Allure Report.

---

## 🏗️ Arquitetura do Projeto

```
automacao-mobile-appium/
├── pom.xml                          ← Gerenciamento de dependências e build (Maven)
├── testng.xml                       ← Suíte de testes e registro de listeners
└── src/test/java/com/minsait/
    ├── driver/
    │   └── DriverManager.java       ← Gerencia o ciclo de vida do AndroidDriver
    ├── pages/
    │   ├── BasePage.java            ← Base com espera explícita e ações reutilizáveis
    │   └── LoginPage.java           ← Mapeamento dos elementos do app (Page Object)
    ├── tests/
    │   ├── BaseTest.java            ← Configuração setUp/tearDown por teste
    │   └── LoginTest.java           ← Casos de teste automatizados
    ├── services/
    │   └── AuthApiService.java      ← Cliente HTTP para testes de API (RestAssured)
    └── utils/
        ├── ScreenshotUtil.java      ← Captura de evidências do dispositivo para o Allure
        └── TestListener.java        ← Listener: logs e evidências automáticas no Allure
```

---

## ⚙️ Stack Tecnológica

| Tecnologia | Versão | Finalidade |
|---|---|---|
| **Java** | 17 | Linguagem de programação |
| **Maven** | — | Build e gerenciamento de dependências |
| **Appium Java Client** | 9.2.2 | Driver para automação mobile Android |
| **UiAutomator2** | — | Engine de automação nativa do Android |
| **Selenium** | 4.21.0 | Base do WebDriver (sessão, esperas, ações) |
| **TestNG** | 7.10.2 | Framework de testes (ciclo de vida, listeners) |
| **RestAssured** | 5.4.0 | Testes e consumo de APIs REST |
| **Allure Report** | 2.27.0 | Geração de relatório visual interativo |
| **AspectJ Weaver** | 1.9.22 | Integração AspectJ para o Allure TestNG |
| **PostgreSQL Driver** | 42.7.3 | Integração com banco de dados relacional |

---

## 🧩 Componentes em Detalhe

### `DriverManager`
- Instância única (Singleton) do `AndroidDriver` por sessão de teste
- Configurado com `UiAutomator2Options` para emulador Android (`emulator-5554`)
- Carrega automaticamente o APK do app (`Android-MyDemoAppRN.1.3.0.build-244.apk`) localizado em `src/test/resources/app/`
- `quitDriver()` usa `try/finally` para garantir que a sessão seja sempre encerrada

### `BasePage`
- Classe base para todos os Page Objects
- Inicializa o `AppiumFieldDecorator` para mapeamento com `@AndroidFindBy`
- Oferece métodos reutilizáveis: `clicar()`, `escrever()`, `obterTexto()`, `isElementoVisivel()`, `voltar()`
- Usa `WebDriverWait` (10 segundos) como mecanismo de sincronização

### `LoginPage` — Page Object Model
Mapeia todos os elementos da tela com `@AndroidFindBy`:
- **Catálogo:** primeiro e segundo produtos via XPath
- **Menu lateral:** botão de abertura e item "Log In"
- **Formulário de login:** campos de usuário, senha, botão de login e mensagem de erro

### `BaseTest`
- `@BeforeMethod`: obtém a instância do driver antes de cada teste
- `@AfterMethod(alwaysRun = true)`: encerra a sessão mesmo que o teste falhe

### `LoginTest` — Casos de Teste

| Teste | Cenário | Severidade |
|---|---|---|
| `deveClicarNosElementosMapeados` | Clica no 1º produto, retorna ao catálogo e clica no 2º produto | 🔴 CRITICAL |
| `deveValidarCamposObrigatoriosDeLogin` | Tenta logar sem dados e valida que a mensagem de erro é exibida | 🟡 NORMAL |

### `AuthApiService`
- Consome a API REST do `reqres.in`
- Envia credenciais via `POST /login` com body JSON
- Registra as requisições/respostas no Allure via `AllureRestAssured` filter
- Expõe `obterTokenAutenticacao()` para uso em outros testes

### `ScreenshotUtil`
- Captura a tela do **dispositivo Android** via `TakesScreenshot` (Appium)
- Anexa a imagem diretamente no Allure Report usando `Allure.addAttachment()`
- Chamado manualmente dentro dos testes após cada passo relevante

### `TestListener`
Listener automático registrado no `testng.xml`:

| Evento | O que registra no Allure |
|---|---|
| `onTestStart` | Log de início com nome do teste e classe |
| `onTestSuccess` | Log com ✅, tempo de execução e print final do device |
| `onTestFailure` | Log com ❌, motivo da falha e print final do device |
| `onTestSkipped` | Log de aviso ⚠️ |

---

## 📊 O que Aparece no Relatório Allure

- **Overview:** Total de testes, taxa de sucesso e trend histórico
- **Behaviors:** Agrupados por `@Epic → @Feature → @Story`
- **Suites:** Organização por classes de teste
- **Timeline:** Linha do tempo de execução
- **Por teste:**
  - Passos detalhados via `Allure.step()`
  - 📸 Screenshots do dispositivo em cada etapa
  - 📄 Log textual com status e tempo de execução
  - Severidade, descrição e categoria

---

## ▶️ Como Executar

### Pré-requisitos

- Java 17+
- Maven 3.8+
- Node.js + Appium instalado globalmente: `npm install -g appium`
- Driver UiAutomator2: `appium driver install uiautomator2`
- Android Studio com um emulador criado (AVD)
- APK do app em `src/test/resources/app/`

### Passo a passo

```bash
# 1. Iniciar o servidor Appium
appium

# 2. Verificar se o emulador está ativo
adb devices

# 3. Executar os testes
mvn clean test

# 4. Abrir o relatório Allure no navegador
allure serve allure-results
```

> **Atenção:** O Appium Server deve estar rodando em `http://127.0.0.1:4723` e o emulador deve aparecer como `emulator-5554` antes de iniciar os testes.

> **Dica:** Para forçar o download de dependências atualizadas, use `mvn clean test -U`.

---

## 🗂️ Recursos Adicionais

- [Documentação oficial do Appium](https://appium.io/docs/en/latest/)
- [Allure Report - Guia TestNG](https://allurereport.org/docs/testng/)
- [RestAssured - Documentação](https://rest-assured.io/)
- [App de demonstração (Sauce Labs)](https://github.com/saucelabs/my-demo-app-rn)

