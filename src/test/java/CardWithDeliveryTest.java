import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CardWithDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
}
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").sendKeys(Keys.CONTROL,"a" + Keys.DELETE);
    }

    @Test
    void shouldReturnSuccess() {
        $("[data-test-id=\"city\"] [placeholder=\"Город\"]").val("Москва");
        $("[data-test-id=\"date\"] [placeholder=\"Дата встречи\"]").val(generateDate(4));
        $("[data-test-id=\"name\"] [name=\"name\"]").val("Иванов Иван");
        $("[data-test-id=\"phone\"] [name=\"phone\"]").val("+79252222222");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $(".button").click();
        $("[data-test-id=\"notification\"] [class=\"notification__title\"]").shouldBe(text("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id=\"notification\"] [class=\"notification__content\"]").shouldBe(text("Встреча успешно забронирована на " + generateDate(4)));
    }
}

