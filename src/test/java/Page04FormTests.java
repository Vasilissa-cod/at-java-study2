import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class Page04FormTests {

    @Test
    void fillForm_and_verifySpans_matchEnteredValues() {
        open("https://slqamsk.github.io/cases/simple-pages/page04.html");

        // На странице предполагается одна основная форма
        SelenideElement form = $(By.tagName("form"));
        form.shouldBe(Condition.exist);

        Map<String, String> fieldKeyToValue = new LinkedHashMap<>();

        // Текстовые и иные поля ввода
        ElementsCollection inputs = form.$$("input");
        for (SelenideElement input : inputs) {
            String type = Optional.ofNullable(input.getAttribute("type")).orElse("text").toLowerCase(Locale.ROOT);
            String key = firstNonBlank(input.getAttribute("id"), input.getAttribute("name"));
            if (key == null || key.isBlank()) {
                // пропускаем анонимные поля, так как сложнее связать со span
                continue;
            }

            switch (type) {
                case "text":
                case "search": {
                    String value = "Иван";
                    input.setValue(value);
                    fieldKeyToValue.put(key, value);
                    break;
                }
                case "email": {
                    String value = "test@example.com";
                    input.setValue(value);
                    fieldKeyToValue.put(key, value);
                    break;
                }
                case "tel": {
                    String value = "+7 999 000-00-00";
                    input.setValue(value);
                    fieldKeyToValue.put(key, value);
                    break;
                }
                case "url": {
                    String value = "https://example.com";
                    input.setValue(value);
                    fieldKeyToValue.put(key, value);
                    break;
                }
                case "number": {
                    String value = "42";
                    input.setValue(value);
                    fieldKeyToValue.put(key, value);
                    break;
                }
                case "password": {
                    String value = "Qwerty123!";
                    input.setValue(value);
                    // пароль может не отображаться в span, но попробуем
                    fieldKeyToValue.put(key, value);
                    break;
                }
                case "radio": {
                    // выбираем первую радио-кнопку в группе
                    if (!input.isSelected()) {
                        input.parent().click();
                    }
                    String value = Optional.ofNullable(input.getAttribute("value")).orElse("on");
                    fieldKeyToValue.put(keyGroupKey(input), value);
                    break;
                }
                case "checkbox": {
                    if (!input.isSelected()) {
                        input.parent().click();
                    }
                    String value = input.isSelected() ? "true" : "false";
                    fieldKeyToValue.put(key, value);
                    break;
                }
                case "submit":
                case "button":
                case "reset":
                default:
                    // пропускаем кнопки на этапе ввода
                    break;
            }
        }

        // textarea
        ElementsCollection textareas = form.$$("textarea");
        for (SelenideElement ta : textareas) {
            String key = firstNonBlank(ta.getAttribute("id"), ta.getAttribute("name"));
            if (key == null || key.isBlank()) continue;
            String value = "Текст комментария";
            ta.setValue(value);
            fieldKeyToValue.put(key, value);
        }

        // select
        ElementsCollection selects = form.$$("select");
        for (SelenideElement select : selects) {
            String key = firstNonBlank(select.getAttribute("id"), select.getAttribute("name"));
            if (key == null || key.isBlank()) continue;
            ElementsCollection options = select.$$("option:not([disabled])");
            if (!options.isEmpty()) {
                SelenideElement option = options.get(Math.min(1, options.size() - 1)); // берем не placeholder, если есть
                String value = Optional.ofNullable(option.getAttribute("value")).filter(v -> !v.isBlank()).orElse(option.getText());
                option.click();
                fieldKeyToValue.put(key, value);
            }
        }

        // Нажимаем все кнопки отправки/применения в форме (на случай, если логика на кнопках)
        ElementsCollection buttons = form.$$("button, input[type='submit'], input[type='button']");
        for (SelenideElement button : buttons) {
            if (button.is(Condition.enabled)) {
                button.click();
            }
        }

        // Пытаемся сопоставить каждый введенный ключ со span на странице и сверить значение
        List<String> failures = new ArrayList<>();
        for (Map.Entry<String, String> entry : fieldKeyToValue.entrySet()) {
            String key = entry.getKey();
            String expected = entry.getValue();

            List<String> spanSelectors = candidateSpanSelectors(key);
            boolean matched = false;
            for (String selector : spanSelectors) {
                ElementsCollection candidates = $$(selector);
                if (!candidates.isEmpty()) {
                    for (SelenideElement span : candidates) {
                        if (span.is(Condition.visible)) {
                            span.shouldHave(Condition.text(expected));
                            matched = true;
                            break;
                        }
                    }
                }
                if (matched) break;
            }
            if (!matched) {
                failures.add("Не найден подходящий span для поля '" + key + "' со значением '" + expected + "'");
            }
        }

        if (!failures.isEmpty()) {
            throw new AssertionError(String.join("\n", failures));
        }
    }

    private static String firstNonBlank(String... values) {
        for (String v : values) {
            if (v != null && !v.isBlank()) return v;
        }
        return null;
    }

    private static String keyGroupKey(SelenideElement radio) {
        // для radio предпочтем name как ключ группы, иначе id
        String name = radio.getAttribute("name");
        if (name != null && !name.isBlank()) return name;
        return firstNonBlank(radio.getAttribute("id"));
    }

    private static List<String> candidateSpanSelectors(String key) {
        // Набор типовых соответствий span-элементов
        // Пробуем по id, data-атрибутам и популярным шаблонам
        List<String> css = new ArrayList<>();
        css.add("span#" + cssEscape(key));
        css.add("#" + cssEscape(key));
        css.add("span#" + cssEscape(key) + "Span");
        css.add("span#" + cssEscape(key) + "-span");
        css.add("span#" + cssEscape(key) + "-value");
        css.add("span#out-" + cssEscape(key));
        css.add("span[data-target='" + key + "']");
        css.add("span[data-bind='" + key + "']");
        css.add("span[for='" + key + "']");
        // как fallback — любой span, содержащий рядом label[for=key]
        css.add("label[for='" + key + "'] ~ span");
        return css;
    }

    private static String cssEscape(String s) {
        // Простейший escape для CSS id
        return s.replace(" ", "\\ ");
    }
}


