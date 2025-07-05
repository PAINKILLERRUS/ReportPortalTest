package dto.widget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dto.find_all_dashboards.WidgetPosition;
import dto.find_all_dashboards.WidgetSize;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddWidgetItem {
    private int widgetId;
    private String widgetName;
    private String widgetType;
    private WidgetPosition widgetPosition;
    private WidgetSize widgetSize;
}
