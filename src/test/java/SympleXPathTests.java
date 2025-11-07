import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.Test;

public class SympleXPathTests {

    @Test
    void testPageH1() {
        String relativeOrAbsoluteUrl;
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//h1").shouldHave(text("Учебная страница для XPath"));
    }

    @Test
    void testPageH2() {
        String relativeOrAbsoluteUrl;
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//p[@class='special-paragraph']").shouldHave(text("Этот параграф особенный - он единственный на странице с таким классом."));
    }

    @Test
    void testPageH3() {
        String relativeOrAbsoluteUrl;
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//p[@class='info-text']").shouldHave(text("Это первый информационный текст."));
    }

    @Test
    void testPageH4() {
        String relativeOrAbsoluteUrl;
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//p[@class='info-text'][2]").shouldHave(text("Это второй информационный текст."));
    }

    @Test
    void testPageH5() {
        String relativeOrAbsoluteUrl;
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//p[@class='info-text'][3]")
                .shouldHave(text("Это третий информационный текст."));
    }

    @Test
    void testExternalLinks(){
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//a[@class='external-link'][1]").shouldHave(text("Внешняя ссылка (Example)"));
        $x("//a[@class='external-link'][2]").shouldHave(text("Внешняя ссылка (Google)"));
    }

    @Test
    void testPageH7() {
        String relativeOrAbsoluteUrl;
        open("https://slqa.ru/cases/xPathSimpleForm/");
        $(withText("Москва")).shouldHave(text("250 единиц"));
    }

    @Test
    void testPageH8() {
        open("https://slqa.ru/cases/xPathSimpleForm/");
        $x("//*[contains(., 'Москва')]").shouldHave (text("250 единиц"));
        $x("//*[contains(., 'Питер')]").shouldHave (text("180 единиц"));
        $x("//*[starts-with(., 'Казахстан')]").shouldHave (text("площадь 2 724 902"));
    }

}
