import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

class TestOrderPizzas {

    @Test
    void testOrderPizzas() {
        open("https://slqamsk.github.io/tmp/xPath01.html");

        $("#pizza2").shouldBe(Condition.visible).click();
        $("#pizza4").shouldBe(Condition.visible).click();

        $("#name").shouldBe(Condition.visible).setValue("Василисса");
        $("#email").shouldBe(Condition.visible).setValue("push@example.com");

        $("button[type='submit']").shouldBe(Condition.enabled).click();

        $("#message")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Пепперони"))
                .shouldHave(Condition.text("Четыре сыра"))
                .shouldNotHave(Condition.text("Маргарита"))
                .shouldNotHave(Condition.text("Гавайская"))
                .shouldNotHave(Condition.text("Вегетарианская"));

        sleep(15000);
    }
}

