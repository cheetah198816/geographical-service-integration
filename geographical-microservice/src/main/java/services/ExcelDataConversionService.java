package services;

import dto.excel.SectionData;
import model.JobEntity;

import java.util.List;

/**
 * Created by chetan on 23.12.2017.
 */
public interface ExcelDataConversionService {

    /**
     * Converts the excel File to Section Data
     *
     * @param jobEntity jobEntity containing the file Name.
     * @return dto of the parsed excel file.
     */
    List<SectionData> convertExcelToDto(JobEntity jobEntity);
}
