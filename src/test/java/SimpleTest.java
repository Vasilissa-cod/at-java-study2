import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SimpleTest {
    @Test
    void test01() {
        open("https://slqamsk.github.io/cases/simple-pages/page01.html");
        $(By.tagName("body")).shouldHave(text("Добро пожаловать"));
    }
    @Test
    void test02() {
        open("https://slqamsk.github.io/cases/simple-pages/page01.html");
        $(By.tagName("body")).shouldNotHave(text("До свидания"));
    }
}
