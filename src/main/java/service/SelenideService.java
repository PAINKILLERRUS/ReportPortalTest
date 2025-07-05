package service;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.NonNull;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SelenideService {
    public static SelenideElement findByText(String text) {
        return $(byText(text));
    }

    public static ElementsCollection findElementsByText(String text) {
        return $$(byText(text));
    }

    public static ElementsCollection findElementsByAttribute(String attributeName, String attributeValue) {
        return $$(byAttribute(attributeName, attributeValue));
    }

    public static ElementsCollection findElementByValue(String attributeValue) {
        return $$(byValue(attributeValue));
    }

    public static SelenideElement findByName(String text) {
        return $(byName(text));
    }

    public static SelenideElement findByValue(String text) {
        return $(byValue(text));
    }

    @NonNull
    public static SelenideElement findByAttribute(String attributeName, String attributeValue) {
        return $(byAttribute(attributeName, attributeValue));
    }
}
