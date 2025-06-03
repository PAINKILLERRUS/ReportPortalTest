package enums;

import lombok.Getter;

@Getter
public enum TestObjectName {
    DASHBOARD("AT_DASHBOARD №"),
    API_KEY("AT_KEY №"),
    WIDGET_NAME("MY_WIDGET№");

    private final String publicName;

    TestObjectName(String publicName) {
        this.publicName = publicName;
    }
}
