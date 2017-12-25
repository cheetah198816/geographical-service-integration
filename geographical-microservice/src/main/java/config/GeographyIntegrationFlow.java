package config;

import mappers.JobEntityMapper;
import mappers.SectionEntityMapper;
import model.JobEntity;
import model.SectionEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.MessageChannel;
import services.ExcelDataConversionService;
import services.GeographicalProcess;


/**
 * Created by chetan on 23.12.2017.
 */
@Configuration
public class GeographyIntegrationFlow {

    @Bean
    public MessageChannel registerJobRequestChannel() {
        return MessageChannels.direct("registerJobRequestChannel").get();
    }


    @Bean
    public MessageChannel registerJobReplyChannel() {
        return MessageChannels.direct("registerJobReplyChannel").get();
    }

    @Bean
    public MessageChannel processExcelChannel() {
        return MessageChannels.direct("processExcelChannel").get();
    }

    @Bean
    public IntegrationFlow registerJobFlow(@Qualifier("registerJobRequestChannel") MessageChannel registerJobRequestChannel, GeographicalProcess geographicalProcess) {
        return IntegrationFlows.from(registerJobRequestChannel)
                .transform(JobEntityMapper::dto2Entity)
                .handle(JobEntity.class, (jobEntity, map) -> geographicalProcess.saveJob(jobEntity))
                .publishSubscribeChannel(s -> s.subscribe(c -> c.channel("registerJobReplyChannel"))
                        .subscribe(c -> c.channel("processExcelChannel")))
                .get();
    }

    @Bean
    public IntegrationFlow processExcelFlow(MessageChannel processExcelChannel,
                                            ExcelDataConversionService excelDataConversionService,
                                            GeographicalProcess geographicalProcess) {
        return IntegrationFlows.from(processExcelChannel)
                .enrichHeaders(headerEnricherSpec -> headerEnricherSpec.headerExpression("JobId", "payload.id"))
                .handle(JobEntity.class, (jobEntity, map) -> excelDataConversionService.convertExcelToDto(jobEntity))
                .split()
                .transform(SectionEntityMapper::dto2Entity)
                .handle(SectionEntity.class, (sectionEntity, map) -> {
                    geographicalProcess.saveSectionEntity(sectionEntity, (Long) map.get("JobId"));
                    return null;
                })
                .get();
    }
}
