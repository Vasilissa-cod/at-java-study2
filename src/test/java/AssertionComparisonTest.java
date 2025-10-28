import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AssertionComparisonTest {

    @Test
    void testEnabledButtonWithThreeTypesOfAssertions() {
        // Открываем тестовую страницу
        open("https://slqamsk.github.io/cases/pages-for-assertions/");
        
        // Находим кнопку
        WebElement button = $(By.id("enabled-button")).getWrappedElement();
        
        // 1. JAVA ASSERT - проверяем, что кнопка активна
        assert button.isEnabled() : "Кнопка должна быть активной (Java Assert)";
        
        // 2. JUNIT ASSERTIONS - проверяем, что кнопка активна
        assertTrue(button.isEnabled(), "Кнопка должна быть активной (JUnit Assertions)");
        
        // 3. SELENIDE CONDITIONS - проверяем, что кнопка активна
        $(By.id("enabled-button")).shouldBe(enabled);
        
        // Дополнительная проверка видимости для полноты картины
        $(By.id("enabled-button")).shouldBe(visible);
    }
    
    @Test
    void testDisabledButtonForComparison() {
        // Открываем тестовую страницу
        open("https://slqamsk.github.io/cases/pages-for-assertions/");
        
        // Находим неактивную кнопку
        WebElement disabledButton = $(By.id("disabled-button")).getWrappedElement();
        
        // Проверяем, что кнопка действительно неактивна
        assert !disabledButton.isEnabled() : "Кнопка должна быть неактивной (Java Assert)";
        assertTrue(!disabledButton.isEnabled(), "Кнопка должна быть неактивной (JUnit Assertions)");
        
        // Selenide проверка неактивности
        $(By.id("disabled-button")).shouldNotBe(enabled);
    }
    
    @Test
    void testButtonTextAndAttributes() {
        // Открываем тестовую страницу
        open("https://slqamsk.github.io/cases/pages-for-assertions/");
        
        // Проверяем текст кнопки
        String buttonText = $(By.id("enabled-button")).getText();
        
        // 1. Java Assert для текста
        assert "Активная кнопка".equals(buttonText) : "Текст кнопки должен быть 'Активная кнопка'";
        
        // 2. JUnit Assertions для текста
        assertEquals("Активная кнопка", buttonText, "Текст кнопки должен совпадать");
        
        // 3. Selenide Conditions для текста
        $(By.id("enabled-button")).shouldHave(text("Активная кнопка"));
        
        // Проверяем CSS класс
        String cssClass = $(By.id("enabled-button")).getAttribute("class");
        assert cssClass.contains("active-button") : "Кнопка должна иметь класс 'active-button'";
        assertTrue(cssClass.contains("active-button"), "CSS класс должен содержать 'active-button'");
        $(By.id("enabled-button")).shouldHave(cssClass("active-button"));
    }
}
