package pl.project.service;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Tom on 17.01.2017.
 */
public interface ImportService {

    void readExcelFile(MultipartFile file) throws Exception;
    boolean checkCellValues(Cell cell, Integer columnIndex);
    boolean validDate(java.util.Date date);

}
