package dto.request;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by chetan on 22.12.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@JsonClassDescription("Dto to register job.")
@NoArgsConstructor
public class RegisterJobRequest {

    @JsonPropertyDescription("File Name")
    @NonNull
    private String fileName;
}
