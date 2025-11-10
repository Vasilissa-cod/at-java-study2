import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class AuthorizationTests {

    @Test
    void test01LoginSuccess() {
        // Открываем страницу авторизации
        open("https://slqa.ru/cases/ChatGPTLogin/");
        sleep(2000); // Пауза для загрузки страницы
        
        // Вводим имя пользователя
        $(By.name("username")).setValue("admin");
        
        // Вводим пароль
        $(By.name("password")).setValue("admin");
        
        // Нажимаем на кнопку Login
        $(By.name("login")).click();
        
        // Проверяем, что мы успешно залогинились
        $(By.tagName("body")).shouldHave(text("Добро пожаловать"));
        
        sleep(5000); // Пауза для просмотра результата
    }
    
    @Test
    void test02LoginWrongPassword() {
        // Открываем страницу авторизации
        open("http://92.51.36.108:7777/sl.qa/exam_tickets/");
        sleep(2000); // Пауза для загрузки страницы
        
        // Вводим имя пользователя
        $(By.name("username")).setValue("admin");
        
        // Вводим некорректный пароль
        $(By.name("password")).setValue("wrongpassword");
        
        // Нажимаем на кнопку Login
        $(By.name("login")).click();
        
        // Проверяем, что выводится сообщение об ошибке
        $(By.name("error")).shouldHave(text("Неверный пароль"));
        
        sleep(5000); // Пауза для просмотра результата
    }
}