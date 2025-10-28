import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class LoginTests {
    @Test
    void test01LoginSuccess() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $(By.id("username")).setValue("standard_user");
        $(By.id("password")).setValue("secret_sauce");
        $(By.id("loginButton")).click();

        $(By.id("greeting")).shouldBe(visible);
        $(By.id("greeting")).shouldHave(text("Welcome, standard_user!"));
    }
    
    @Test
    void test02LoginWrongPassword() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $(By.id("username")).setValue("standard_user");
        $(By.id("password")).setValue("wrong_password");
        $(By.id("loginButton")).click();
        $(By.id("message")).shouldBe(visible);
        $(By.id("message")).shouldHave(text("Invalid username or password."));
        $(By.id("greeting")).shouldNotBe(visible);
    }
    
    @Test
    void test03LoginLockedUser() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $(By.id("username")).setValue("locked_out_user");
        $(By.id("password")).setValue("secret_sauce");
        $(By.id("loginButton")).click();
        $(By.id("message")).shouldBe(visible);
        $(By.id("message")).shouldHave(text("Пользователь заблокирован."));
        $(By.id("greeting")).shouldNotBe(visible);
    }
}