package dto.excel;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

/**
 * Created by chetan on 23.12.2017.
 */
@Data
public class GeographicalClassData {

    @JsonPropertyDescription("Id of the Geographical Class.")
    private Long id;

    @JsonPropertyDescription("Name of the Geographical Class.")
    private String name;

    @JsonPropertyDescription("Code of the Geographical Class.")
    private String code;
}
