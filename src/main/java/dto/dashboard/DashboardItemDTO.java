package dto.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import patterns.factory_method.Dashboard;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DashboardItemDTO implements Dashboard {
    private String name;
    private String description;
    private String widgetName;
}
