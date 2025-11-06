import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SimpleElementsTests {
    @Test
    void testElementSearchScenarios() {
        // Открываем локальную страницу (предполагается, что файл лежит в корне проекта или подаётся через file://)
        open("https://slqamsk.github.io/cases/simple-pages/page02.html");
        // 1. Поиск первого элемента по тегу <p>
        $(By.tagName("p")).shouldHave(text("Первый абзац"));
        // 2. Поиск 2-го и 3-го элемента по тегу <p>
        $$(By.tagName("p")).get(1).shouldHave(text("Второй абзац"));
        $$(By.tagName("p")).get(2).shouldHave(text("Третий абзац"));
        // 3. Поиск по ID
        $(By.id("unique-id-element")).shouldHave(text("Элемент с уникальным ID"));
        // 4. Уникальный класс (только у одного элемента)
        $(By.className("unique-class")).shouldHave(text("Элемент с уникальным классом"));
        // 5. Повторяющийся класс — взять второй элемент
        $$(By.className("repeating-class")).get(1).shouldHave(text("Второй элемент с repeating-class"));
        // 6. Элемент с несколькими классами, один из которых уникальный
        // Ищем по уникальному классу
        $(By.className("special-button")).shouldHave(text("Уникальная кнопка (special-button)"));
        // 7. Поиск по атрибуту name
        $(By.name("username")).shouldBe(visible).setValue("vasillisa");
        $(By.name("password")).setValue("secret123");
        $(By.name("contact")).setValue("vasilissa@example.com");
        // Проверка, что кнопка с name="submit-btn" существует
        $(By.name("submit-btn")).shouldBe(visible);
        // Также проверим поля из второй формы
        $(By.name("first-name")).setValue("Vasilissa");
        $(By.name("last-name")).setValue("Obolenskaya");
        $(By.name("form-submit")).shouldBe(visible);
    }
    @Test
    void testButtonClickAndLogOutput() {
        open("https://slqamsk.github.io/cases/simple-pages/page02.html");
        // Клик по уникальной кнопке
        $(By.className("special-button")).click();
        $(By.id("log-general")).shouldHave(text("Нажата кнопка: \"Уникальная кнопка (special-button)\""));
        // Заполняем первую форму и кликаем
        $(By.name("username")).setValue("testuser");
        $(By.name("password")).setValue("123");
        $(By.name("contact")).setValue("test@test.ru");
        $(By.name("submit-btn")).click();

        $(By.id("log-form1")).shouldHave(text("username: testuser"));
        $(By.id("log-form1")).shouldHave(text("password: 123"));
        $(By.id("log-form1")).shouldHave(text("contact: test@test.ru"));
        // Заполняем вторую форму
        $(By.name("first-name")).setValue("Алиса");
        $(By.name("last-name")).setValue("Пухтель");
        $(By.name("form-submit")).click();
        $(By.id("log-form2")).shouldHave(text("first-name: Алиса"));
        $(By.id("log-form2")).shouldHave(text("last-name: Пухтель"));
    }
}
