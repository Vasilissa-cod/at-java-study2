import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

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
    @Test
        //Повторный расчёт комиссии без закрытия формы - тест 1: проверка для 2000 рублей
    void testRepeatCalculationStep1() {
        //Предусловие 1: Открыть форму
        open("https://slqa.ru/cases/fc/v01");

        //Предусловие 2: Проверить расчёт комиссии для суммы 2000 рублей
        $(By.name("sum")).setValue("2000");
        $(By.name("submit")).click();
        $(By.name("com")).shouldHave(text("20"));
        $(By.name("total")).shouldHave(text("2020"));
    }

    @Test
        //Повторный расчёт комиссии без закрытия формы - тест 2: проверка для 500 рублей
    void testRepeatCalculationStep2() {
        //Шаг 1: Открыть форму и ввести сумму 500 рублей
        open("https://slqa.ru/cases/fc/v01");
        $(By.name("sum")).setValue("500");

        //Шаг 2: Нажать кнопку "Рассчитать"
        $(By.name("submit")).click();

        //Шаг 3: Убедиться, что комиссия составит 10 рублей, сумма к оплате 510 рублей
        $(By.name("com")).shouldHave(text("10"));
        $(By.name("total")).shouldHave(text("510"));
    }
}