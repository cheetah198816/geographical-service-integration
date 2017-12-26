package services.impl;

import model.GeographicalClassesEntity;
import model.JobEntity;
import model.SectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repositories.GeographicalClassesRepository;
import repositories.JobRepository;
import repositories.SectionRepository;
import services.GeographicalProcess;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chetan on 25.12.2017.
 */
@Service
public class GeographicalProcessImpl implements GeographicalProcess {

    private JobRepository jobRepository;

    private SectionRepository sectionRepository;

    private GeographicalClassesRepository geographicalClassesRepository;

    @Autowired
    public GeographicalProcessImpl(JobRepository jobRepository,
                                   SectionRepository sectionRepository,
                                   GeographicalClassesRepository geographicalClassesRepository) {
        this.sectionRepository = sectionRepository;
        this.geographicalClassesRepository = geographicalClassesRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public JobEntity findById(Long jobId) {
        return jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job Entity not found for the job Id : " + jobId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SectionEntity> findByNameAndCode(String name, String code, Long jobId) {
        if (name != null && code != null) {
            return sectionRepository.findByNameAndCode(name, code, jobId);
        } else if (name != null) {
            return sectionRepository.findByName(name, jobId);
        } else if (code != null) {
            return sectionRepository.findByCode(code, jobId);
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public JobEntity saveJob(JobEntity jobEntity) {
        return jobRepository.save(jobEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SectionEntity saveSectionEntity(SectionEntity sectionEntity, Long jobId) {
        sectionEntity.setJob(jobRepository.findById(jobId).get());
        SectionEntity savedSection = sectionRepository.save(sectionEntity);
        sectionEntity.getGeographicalClassesEntityList().stream().forEach(geographicalClassesEntity -> saveGeographicalClassesEntity(savedSection, geographicalClassesEntity));
        return savedSection;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private GeographicalClassesEntity saveGeographicalClassesEntity(SectionEntity sectionEntity, GeographicalClassesEntity geographicalClassesEntity) {
        geographicalClassesEntity.setSection(sectionEntity);
        return geographicalClassesRepository.save(geographicalClassesEntity);
    }
}
