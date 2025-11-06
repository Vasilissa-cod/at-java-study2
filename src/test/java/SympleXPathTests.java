import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.Test;

public class SympleXPathTests {
    @Test
    void testTextSearch(){
        open("https://slqa.ru/cases/xPathSimpleForm/");
        $(withText("Москва")).shouldHave(text("250 единиц"));
    }

    @Test
    void testNotUniqueClass1() {
        open("https://slqa.ru/cases/xPathSimpleForm/");
        $x("//*[contains(., 'Москва')]").shouldHave(text("250 единиц"));
        $x("//*[contains(., 'Питер')]").shouldHave(text("180 единиц"));
        $x("//*[starts-with(., 'Казахстан')]").shouldHave(text("площадь 2 724 902"));
    }
}

