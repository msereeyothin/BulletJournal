package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A java record to represent a Task
 *
 * @param name Name of the task
 * @param description Description for the task
 * @param complete if the task is completed
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("complete") boolean complete) {
}
