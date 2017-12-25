package dto.excel;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

import java.util.List;

/**
 * Created by chetan on 23.12.2017.
 */
@Data
public class SectionData {

    @JsonPropertyDescription("Id of the Section Data.")
    private Long id;

    @JsonPropertyDescription("Name of the Section Data.")
    private String name;

    @JsonPropertyDescription("List of Geographical Classes.")
    private List<GeographicalClassData> geographicalClassDataList;
}
