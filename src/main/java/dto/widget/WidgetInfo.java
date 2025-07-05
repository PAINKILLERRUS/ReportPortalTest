package dto.widget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dto.find_all_dashboards.Content;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WidgetInfo {
    private String description;
    private String owner;
    private int id;
    private String name;
    private String widgetType;
    private ContentParameters contentParameters;
    private List<Object> appliedFilters;
    private Content content;
}
