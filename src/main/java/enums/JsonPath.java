package enums;

import lombok.Getter;

@Getter
public enum JsonPath {

    CREATE_WIDGET_JSON("create_widget.json"),
    ADD_WIDGET_JSON("widget.json");

    private final String jsonPathName;

    JsonPath(String jsonPathName) {
        this.jsonPathName = jsonPathName;
    }
}
