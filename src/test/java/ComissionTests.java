import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ComissionTests {

    @Test
    //Повторный расчёт комиссии без закрытия формы - тест 1: проверка для 2000 рублей
    void testRepeatCalculationStep1() {
        //Предусловие 1: Открыть форму
        open("https://slqa.ru/cases/fc/v01");
        sleep(2000); // Пауза для полной загрузки страницы
        
        //Предусловие 2: Проверить расчёт комиссии для суммы 2000 рублей
        $(By.name("sum")).setValue("2000");
        $(By.name("submit")).click();
        $(By.name("com")).shouldHave(text("20"));
        $(By.name("total")).shouldHave(text("2020"));
        
        sleep(5000); // Пауза для просмотра результата
    }
    
    @Test
    //Повторный расчёт комиссии без закрытия формы - тест 2: проверка для 500 рублей
    void testRepeatCalculationStep2() {
        //Шаг 1: Открыть форму и ввести сумму 500 рублей
        open("https://slqa.ru/cases/fc/v01");
        sleep(2000); // Пауза для полной загрузки страницы
        $(By.name("sum")).setValue("500");
        
        //Шаг 2: Нажать кнопку "Рассчитать"
        $(By.name("submit")).click();
        
        //Шаг 3: Убедиться, что комиссия составит 10 рублей, сумма к оплате 510 рублей
        $(By.name("com")).shouldHave(text("10"));
        $(By.name("total")).shouldHave(text("510"));
        
        sleep(5000); // Пауза для просмотра результата
    }
}