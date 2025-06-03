package dto.find_all_dashboards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {
    private String owner;
    private int id;
    private String name;
    private List<Widget> widgets;
    private String description;
}
