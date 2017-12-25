package repositories;

import model.SectionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chetan on 22.12.2017.
 */
@Repository
public interface SectionRepository extends CrudRepository<SectionEntity, Long> {

    @Query("select s from SectionEntity s, GeographicalClassesEntity g " +
            "where s.id = g.section.id and g.name = :name and s.job.id=:jobId")
    List<SectionEntity> findByName(@Param("name") String name, @Param("jobId") Long jobId);

    @Query("select s from SectionEntity s, GeographicalClassesEntity g " +
            "where s.id = g.section.id and g.code = :code and s.job.id=:jobId")
    List<SectionEntity> findByCode(@Param("code") String code, @Param("jobId") Long jobId);

    @Query("select s from SectionEntity s, GeographicalClassesEntity g " +
            "where s.id = g.section.id and g.name = :name and g.code = :code and s.job.id=:jobId")
    List<SectionEntity> findByNameAndCode(@Param("name") String name, @Param("code") String code, @Param("jobId") Long jobId);

}
