package POM.selenide;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static service.SelenideService.findByText;

@Getter
public class WidgetPage {

    private final SelenideElement selectWidgetTypeButton = $(findByText("Test case search"));
    private final SelenideElement nextStepButton = $(byText("Next step"));
    private final SelenideElement addButton = $(byText("Add"));

    private final SelenideElement widgetName = $(byAttribute("placeholder", "Enter widget name"));
    private final SelenideElement widgetDescription = $(byAttribute("placeholder", "Enter widget description"));
}
