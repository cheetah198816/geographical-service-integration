package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by chetan on 22.12.2017.
 */
@Entity
@Table(name = "section")
@SequenceGenerator(name = "seq_section", sequenceName = "seq_section")
@Data
@EqualsAndHashCode(exclude = "job,geographicalClassesEntityList")
public class SectionEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "seq_section")
    private Long id;

    private String sectionName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<GeographicalClassesEntity> geographicalClassesEntityList;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobEntity job;
}
