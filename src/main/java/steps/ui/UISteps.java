package steps.ui;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.interactable;
import static service.SelenideService.findByText;

public class UISteps {

    @Step("Находим элемент ={element} на странице и кликаем")
    public Object click(SelenideElement element) {
        element.scrollIntoView(true).shouldBe(interactable, Duration.ofSeconds(10)).click();
        return this;
    }

    @Step("Наводим мышку и кликаем по элементу ={element}")
    public Object hoverAndClick(SelenideElement element) {
        element.hover().shouldBe(interactable, Duration.ofSeconds(15)).click();
        return this;
    }

    @Step("Двойной клик по элементу")
    public Object doubleClick(SelenideElement element) {
        element.doubleClick();
        return this;
    }

    @Step("Находим элемент ={element} на странице и проверяем соответствует ли он условиям ={conditions}")
    public Object checkConditions(SelenideElement element, WebElementCondition... conditions) {
        element.scrollTo().should(conditions);
        return this;
    }

    @Step("Находим элемент {element} на странице и проверяем соответствует ли он условиям ={conditions} на протяжении ={duration} секунд")
    public Object checkConditions(SelenideElement element, WebElementCondition condition, long duration) {
        element.scrollTo().should(condition, Duration.ofSeconds(duration));
        return this;
    }

    @Step("Находим на странице элемент с текстом ={text} и проверяем соответствует ли он условиям ={conditions} ")
    public Object checkConditions(String text, WebElementCondition... conditions) {
        findByText(text).should(conditions);
        return this;
    }

    @Step("Вводим текст ={value} в поле ={element}")
    public Object setValue(SelenideElement element, String value) {
        element.setValue(value);
        return this;
    }

    @Step("Наводим курсор и кликаем правой кнопкой мыши")
    public void hoverAndContextClick(SelenideElement element) {
        element.hover().contextClick();
    }

    @Step("Очищаем поле {element}")
    public void clear(SelenideElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);
    }
}
