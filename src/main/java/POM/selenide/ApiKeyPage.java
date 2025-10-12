package POM.selenide;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class ApiKeyPage {

    private final SelenideElement userProfileButton = $(".sidebar__bottom-block--Vo6QL");
    private final SelenideElement profileButton = $$("a[href='#userProfile']").get(1);

    private final SelenideElement apiKeysButton = $$(".navigationTabs__tab--go2el").get(1);
    private final SelenideElement generateApiKeysButton = $$(".ghostButton__ghost-button--r7c9T.ghostButton__color-topaz--Z_OvX.ghostButton__filled-icon--HoBWw").get(2);
    private final SelenideElement generating = $(".bigButton__big-button--BmG4Q.bigButton__color-booger--EpRlL");
    private final SelenideElement closeButton = $(".bigButton__big-button--BmG4Q.bigButton__color-gray-60--fySwY");
    private final SelenideElement revokeTheKeyButton = $$(".ghostButton__ghost-button--r7c9T.ghostButton__transparent-border-hover--Yf8wN.ghostButton__transparent-background--oqH_H").get(0);
    private final SelenideElement revokeButton = $(".bigButton__big-button--BmG4Q.bigButton__color-tomato--jXOiC");

    private final SelenideElement generatingApiKeyWindowTittle = $(".modalHeader__modal-title--pusR8");
    private final SelenideElement infoMessageAboutRevokingApiKey = $("._title_14lm6_32");

    private final SelenideElement apiKeyNameField = $(".input__input--iYEmM.type-text.variant-standard.input__error--qY4dY");
}