package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.scene.paint.Color;

/**
 * A java record to represent a Theme
 *
 * @param name Name of the Theme
 * @param font The font in use
 * @param primaryColor The main Background Color
 * @param secondaryColor The top bar color
 * @param fontColor The color of the font
 */
public record ThemeJson(
    @JsonProperty("name") String name,
    @JsonProperty("font") FontJson font,
    @JsonProperty("primaryColor") Color primaryColor,
    @JsonProperty("secondaryColor") Color secondaryColor,
    @JsonProperty("fontColor") Color fontColor
) {
}
