package mappers;

import dto.excel.GeographicalClassData;
import dto.excel.SectionData;
import model.GeographicalClassesEntity;
import model.SectionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chetan on 23.12.2017.
 */
@Component
public class SectionEntityMapper {

    public static SectionEntity dto2Entity(SectionData sectionData) {
        final SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setSectionName(sectionData.getName());
        final List<GeographicalClassesEntity> geographicalClassesEntities = sectionData.getGeographicalClassDataList()
                .stream()
                .map(geographicalClassData -> GeographicalEntityMapper.dto2entity(geographicalClassData))
                .collect(Collectors.toList());
        sectionEntity.setGeographicalClassesEntityList(geographicalClassesEntities);
        return sectionEntity;
    }


    public static SectionData entity2Dto(SectionEntity sectionEntity) {
        final SectionData sectionData = new SectionData();
        sectionData.setId(sectionEntity.getId());
        sectionData.setName(sectionEntity.getSectionName());
        final List<GeographicalClassData> geographicalClassDatas = sectionEntity.getGeographicalClassesEntityList()
                .stream()
                .map(geographicalClassesEntity -> GeographicalEntityMapper.entity2Dto(geographicalClassesEntity))
                .collect(Collectors.toList());

        sectionData.setGeographicalClassDataList(geographicalClassDatas);

        return sectionData;
    }
}
