package repositories;

import model.GeographicalClassesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chetan on 22.12.2017.
 */
@Repository
public interface GeographicalClassesRepository extends CrudRepository<GeographicalClassesEntity, Long> {
}
