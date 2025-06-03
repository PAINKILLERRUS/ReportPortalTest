package dto.find_all_dashboards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
    private int number;
    private int size;
    private int totalElements;
    private int totalPages;
}
