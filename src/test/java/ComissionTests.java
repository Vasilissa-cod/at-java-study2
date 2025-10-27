import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ComissionTests {

    @Test
        //Проверить, что для суммы 2000 рублей форма работает
    void test01UsualSum() {
        //Открыть страницу
        open("https://slqa.ru/cases/fc/v01");
        //Ввести 2000 в поле "Сумма перевода"
        $(By.name("sum")).setValue("2000");
        //Нажать на кнопку
        $(By.name("submit")).click();
        //Проверить комиссию и проверить общую сумму к оплате
        $(By.name("com")).shouldHave(text("20"));
        $(By.name("total")).shouldHave(text("2020"));
    }
    @Test
//Проверить, что для суммы "q" форма работает
    void test02InvalidSum() {
        //Открыть страницу
        open("https://slqa.ru/cases/fc/v01");
        //Ввести "q" в поле "Сумма перевода"
        $(By.name("sum")).setValue("q");
        //Нажать на кнопку
        $(By.name("submit")).click();
        //Проверить, что появилось сообщение об ошибке
        $(By.name("error")).shouldHave(text("Неверный формат суммы"));
    }
    @BeforeEach
    void setUp() {
        int timeout = 10000; // 10 секунд
        pageLoadTimeout = 30000; // 30 секунд для загрузки страницы
        browser = "chrome";
        headless = false; // Показываем браузер для отладки
    }

    @Test
    void testRepeatCalculationStep1() {
        open("https://slqa.ru/cases/fc/v01");
        sleep(2000); // Пауза для полной загрузки страницы
        $(By.name("sum")).setValue("2000");
        $(By.name("submit")).click();
        $(By.name("com")).shouldHave(text("20"));
        $(By.name("total")).shouldHave(text("2020"));
    }

    @Test
    void testRepeatCalculationStep2() {
        open("https://slqa.ru/cases/fc/v01");
        sleep(2000); // Пауза для полной загрузки страницы
        $(By.name("sum")).setValue("500");
        $(By.name("submit")).click();
        $(By.name("com")).shouldHave(text("10"));
        $(By.name("total")).shouldHave(text("510"));
    }
}