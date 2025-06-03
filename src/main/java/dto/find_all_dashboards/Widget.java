package dto.find_all_dashboards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Widget {
    private String widgetName;
    private int widgetId;
    private String widgetType;
    private WidgetSize widgetSize;
    private WidgetPosition widgetPosition;
    private WidgetOptions widgetOptions;
}
