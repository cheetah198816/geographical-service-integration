package services.impl;

import config.FileConfiguration;
import dto.excel.GeographicalClassData;
import dto.excel.SectionData;
import model.JobEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.ExcelDataConversionService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chetan on 23.12.2017.
 */
@Service
public class ExcelDataConversionImpl implements ExcelDataConversionService {

    private FileConfiguration fileConfiguration;

    @Autowired
    public ExcelDataConversionImpl(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
    }

    @Override
    public List<SectionData> convertExcelToDto(JobEntity jobEntity) {
        final FileInputStream excelFile;
        final List<SectionData> sectionDataList = new ArrayList<>();
        final Workbook workbook;
        final int numberOfSheets;
        try {
            excelFile = new FileInputStream(new File(fileConfiguration.getBaseFilePath() + jobEntity.getFileName()));
            if (jobEntity.getFileName().contains(".xlsx")) {
                workbook = new XSSFWorkbook(excelFile);
            } else {
                workbook = new HSSFWorkbook(excelFile);
            }
            numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet s = workbook.getSheetAt(i);
                Iterator<Row> iterator = s.iterator();
                while (iterator.hasNext()) {
                    setRowData(sectionDataList, iterator);
                }
            }
            return sectionDataList;
        } catch (IOException ex) {

        }
        return sectionDataList;
    }

    private void setRowData(List<SectionData> sectionDataList, Iterator<Row> iterator) {
        final SectionData sectionData = new SectionData();
        final Row currentRow = iterator.next();
        final Iterator<Cell> cellIterator = currentRow.iterator();
        final List<GeographicalClassData> geographicalClassDatas = new ArrayList<>();
        while (cellIterator.hasNext()) {
            final Cell currentCell = cellIterator.next();
            setCellData(sectionData, cellIterator, geographicalClassDatas, currentCell);
        }
        sectionDataList.add(sectionData);
    }

    private void setCellData(SectionData sectionData, Iterator<Cell> cellIterator, List<GeographicalClassData> geographicalClassDatas, Cell currentCell) {
        if (currentCell.getColumnIndex() == 0) {
            sectionData.setName(currentCell.getStringCellValue());
            sectionData.setGeographicalClassDataList(geographicalClassDatas);
        } else {
            GeographicalClassData geographicalClassData = new GeographicalClassData();
            geographicalClassData.setName(currentCell.getStringCellValue());
            geographicalClassData.setCode(cellIterator.next().getStringCellValue());
            geographicalClassDatas.add(geographicalClassData);
        }
    }
}
