package repositories;

import model.JobEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by chetan on 22.12.2017.
 */
@Repository
public interface JobRepository extends CrudRepository<JobEntity, Long> {

    Optional<JobEntity> findById(Long id);
}
