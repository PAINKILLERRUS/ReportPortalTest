package enums;

import lombok.Getter;

@Getter
public enum ServerMessage {
    ERROR_MESSAGE_EMPTY_VALUE("Incorrect Request. [Field 'name' should not contain only white spaces and shouldn't be empty. Field 'name' should have size from '3' to '128'.] "),
    ERROR_MESSAGE_INVALID_SIZE("Incorrect Request. [Field 'name' should have size from '3' to '128'.] "),
    DELETE_DASHBOARD_RESPONSE("Dashboard with ID = '{id}' successfully deleted."),
    DELETE_API_KEY_RESPONSE("Api key with ID = '{id}' was successfully deleted.");

    private final String message;

    ServerMessage(String message) {
        this.message = message;
    }
}
