import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ChatGPTLoginTests {

    @Test
    void testSuccessfulLogin() {
        // Открываем страницу логина ChatGPT
        open("https://slqa.ru/cases/ChatGPTLogin/");
        sleep(2000); // Пауза для загрузки страницы
        
        // Вводим корректное имя пользователя
        $(By.id("username")).setValue("standard_user");
        
        // Вводим корректный пароль
        $(By.id("password")).setValue("secret_sauce");
        
        // Нажимаем на кнопку "Войти"
        $(By.id("loginButton")).click();
        
        // Проверяем успешный вход - ищем элемент приветствия
        $(By.id("greeting")).shouldBe(visible);
        $(By.id("greeting")).shouldHave(text("Welcome, standard_user!"));
        
        sleep(3000); // Пауза для просмотра результата
    }
    
    @Test
    void testWrongPassword() {
        // Открываем страницу логина ChatGPT
        open("https://slqa.ru/cases/ChatGPTLogin/");
        sleep(2000); // Пауза для загрузки страницы
        
        // Вводим корректное имя пользователя
        $(By.id("username")).setValue("standard_user");
        
        // Вводим неправильный пароль
        $(By.id("password")).setValue("wrong_password");
        
        // Нажимаем на кнопку "Войти"
        $(By.id("loginButton")).click();
        
        // Проверяем, что появилось сообщение об ошибке
        $(By.id("message")).shouldBe(visible);
        $(By.id("message")).shouldHave(text("Invalid username or password."));
        
        sleep(3000); // Пауза для просмотра результата
    }
}
