import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.Test;

public class FormXTests {
    @Test
    void testFormSubmission_displaysEnteredValues() {
        open("https://slqamsk.github.io/cases/simple-pages/page04.html");

        String name = "Vasilissa";
        String email = "vasilissa@example.com";
        String password = "Qwerty123!";

        $("#username").setValue(name);
        $("#email").setValue(email);
        $("#password").setValue(password);
        $("button[type='submit']").click();

        $("#reginfo #username").shouldHave(text(name));
        $("#reginfo #email").shouldHave(text(email));
        $("#reginfo #password").shouldHave(text(password));
    }

    @Test
    void testFormSubmission_withXValues_displaysXValues() {
        open("https://slqamsk.github.io/cases/simple-pages/page04.html");

        String name = "x";
        String email = "x@x.x";
        String password = "xX12345!";

        $("#username").setValue(name);
        $("#email").setValue(email);
        $("#password").setValue(password);
        $("button[type='submit']").click();

        $("#reginfo #username").shouldHave(text(name));
        $("#reginfo #email").shouldHave(text(email));
        $("#reginfo #password").shouldHave(text(password));
    }
}


