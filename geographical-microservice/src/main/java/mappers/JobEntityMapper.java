package mappers;

import dto.request.RegisterJobRequest;
import model.JobEntity;
import org.springframework.stereotype.Component;

/**
 * Created by chetan on 23.12.2017.
 */
@Component
public class JobEntityMapper {

    public static JobEntity dto2Entity(RegisterJobRequest registerJobRequest) {
        final JobEntity jobEntity = new JobEntity();
        jobEntity.setFileName(registerJobRequest.getFileName());
        jobEntity.setJobName("parseExcel");

        return jobEntity;
    }
}
