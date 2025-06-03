package dto.find_all_dashboards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WidgetOptions {
    private boolean zoom;
    private String timeline;
    private String viewMode;
    private boolean latest;
    private boolean includeMethods;
    private String launchNameFilter;
    private boolean excludeSkipped;
}
