package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dto.dashboard.DashboardItemDTO;
import dto.widget.WidgetDTO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HubDTO {
    private DashboardItemDTO dashboardItemDTO;
    private WidgetDTO widget;
}
