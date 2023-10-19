package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A java record to represent a week.
 *
 * @param name      The name of the week
 * @param days      The list of days in the week
 * @param theme     The theme of the week
 * @param maxEvents The max number of events
 * @param maxTasks  The max number of tasks
 * @param themeList The list of themes
 */
public record WeekJson(
    @JsonProperty("name") String name,
    @JsonProperty("days") List<DayJson> days,
    @JsonProperty("theme") ThemeJson theme,
    @JsonProperty("maxEvents") int maxEvents,
    @JsonProperty("maxTasks") int maxTasks,
    @JsonProperty("themeList") List<ThemeJson> themeList
) {
}
