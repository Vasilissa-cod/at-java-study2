import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class ChatGPTLoginFixedTests {
    
    @Test
    void test01LoginSuccess() {
        // Открываем страницу логина
        open("https://slqa.ru/cases/ChatGPTLogin/");
        sleep(2000); // Пауза для загрузки страницы
        
        // Вводим имя пользователя
        $(By.id("username")).setValue("standard_user");
        
        // Вводим пароль
        $(By.id("password")).setValue("secret_sauce");
        
        // Нажимаем кнопку "Войти"
        $(By.id("loginButton")).click();
        
        // Проверяем успешный вход по элементу greeting
        $(By.id("greeting")).shouldBe(visible);
        $(By.id("greeting")).shouldHave(text("Welcome, standard_user!"));
        
        sleep(3000); // Пауза для просмотра результата
    }
    
    @Test
    void test02LoginWrongPassword() {
        // Открываем страницу логина
        open("https://slqa.ru/cases/ChatGPTLogin/");
        sleep(2000); // Пауза для загрузки страницы
        
        // Вводим корректное имя пользователя
        $(By.id("username")).setValue("standard_user");
        
        // Вводим неправильный пароль
        $(By.id("password")).setValue("wrong_password");
        
        // Нажимаем кнопку "Войти"
        $(By.id("loginButton")).click();
        
        // Проверяем, что появилось сообщение об ошибке
        $(By.id("message")).shouldBe(visible);
        $(By.id("message")).shouldHave(text("Invalid username or password."));
        
        sleep(3000); // Пауза для просмотра результата
    }
}
