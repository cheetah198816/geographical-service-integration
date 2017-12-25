package gateway;

import dto.request.RegisterJobRequest;
import model.JobEntity;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by chetan on 23.12.2017.
 */
@MessagingGateway
public interface GeographyGateway {

    @Gateway(requestChannel = "registerJobRequestChannel", replyChannel = "registerJobReplyChannel")
    JobEntity registerJob(RegisterJobRequest registerJobRequest);
}
