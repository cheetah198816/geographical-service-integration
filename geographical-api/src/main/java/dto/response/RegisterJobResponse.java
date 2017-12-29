package dto.response;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;


/**
 * Created by chetan on 22.12.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@JsonClassDescription("Response Dto containing the registered job id.")
public class RegisterJobResponse {

    @JsonPropertyDescription("Job Id")
    private Long jobId;
}
