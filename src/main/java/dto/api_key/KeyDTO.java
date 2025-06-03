package dto.api_key;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyDTO {
    private int id;
    private String name;
    private int user_id;
    private String created_at;
    private String api_key;
}
