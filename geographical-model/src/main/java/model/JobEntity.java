package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by chetan on 22.12.2017.
 */

@Entity
@Table(name = "job")
@SequenceGenerator(name = "seq_job", sequenceName = "seq_job")
@Data
@EqualsAndHashCode(exclude = "sectionEntityList")
public class JobEntity {

    @Id
    @GeneratedValue(generator = "seq_job")
    private Long id;

    private String jobName;

    private String fileName;

    @OneToMany(mappedBy = "job", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SectionEntity> sectionEntityList;
}
