package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class AppConfig {
    private String login;
    private String password;
    private String grantType;
    private String baseUrl;
}
