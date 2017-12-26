package services.impl;

import dto.excel.SectionData;
import dto.request.RegisterJobRequest;
import dto.response.GetResultsResponse;
import dto.response.RegisterJobResponse;
import dto.response.SearchResultsResponse;
import gateway.GeographyGateway;
import mappers.SectionEntityMapper;
import model.JobEntity;
import model.SectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import services.GeographicalProcess;
import services.GeographicalService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chetan on 22.12.2017.
 */
@Service
public class GeographicalServiceImpl implements GeographicalService {

    private GeographyGateway geographyGateway;

    private GeographicalProcess geographicalProcess;

    @Autowired
    public GeographicalServiceImpl(GeographyGateway geographyGateway, GeographicalProcess geographicalProcess) {
        this.geographyGateway = geographyGateway;
        this.geographicalProcess = geographicalProcess;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RegisterJobResponse registerJob(RegisterJobRequest registerJobRequest) {
        final RegisterJobResponse registerJobResponse = new RegisterJobResponse();
        /** Using Integration **/
        final JobEntity jobEntity = geographyGateway.registerJob(registerJobRequest);
        registerJobResponse.setJobId(jobEntity.getId());
        return registerJobResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public GetResultsResponse getResults(Long jobId) {
        final GetResultsResponse getResultsResponse = new GetResultsResponse();
        final JobEntity jobEntity = geographicalProcess.findById(jobId);
        getResultsResponse.setJobId(jobEntity.getId());
        getResultsResponse.setJobName(jobEntity.getJobName());
        final List<SectionData> sectionDatas = jobEntity.getSectionEntityList().stream()
                .map(sectionEntity -> SectionEntityMapper.entity2Dto(sectionEntity))
                .collect(Collectors.toList());
        getResultsResponse.setSectionDatas(sectionDatas);

        return getResultsResponse;
    }

    @Override
    public SearchResultsResponse searchResults(String code, String name, Long jobId) {
        final SearchResultsResponse searchResultsResponse = new SearchResultsResponse();
        final List<SectionEntity> sectionEntities = geographicalProcess.findByNameAndCode(name, code, jobId);
        final List<SectionData> sectionDatas = sectionEntities.stream().map(sectionEntity -> SectionEntityMapper.entity2Dto(sectionEntity)).collect(Collectors.toList());
        searchResultsResponse.setSectionDatas(sectionDatas);
        return searchResultsResponse;
    }
}
