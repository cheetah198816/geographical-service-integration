package repositories;

import model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by chetan on 22.12.2017.
 */
@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

    Optional<JobEntity> findById(Long id);
}
