package pl.project.service;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.project.exception.CustomException;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * Created by Tom on 17.01.2017.
 */
@Service
@Transactional
public class ImportServiceImpl implements ImportService {


    @Override
    public void readExcelFile(MultipartFile file) throws Exception {

        Workbook workbook;
        DataFormatter df = new DataFormatter();
        ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
        if (file.getOriginalFilename().endsWith("xls"))
            workbook = new HSSFWorkbook(bis);
        else
            workbook = new XSSFWorkbook(bis);

        Sheet sheet = workbook.getSheetAt(0);
        Integer rowIndex = 0;
        for (Row row : sheet) {
            rowIndex = row.getRowNum()+1;
            if(row.getRowNum() == 0 && !row.getCell(0).getStringCellValue().equalsIgnoreCase("system"))
                throw new CustomException("Bad content of file.");
            if (row.getRowNum() != 0) {
                Iterator<Cell> cellIterator = row.cellIterator();
                Integer columnIndex = 0;
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    if(!checkCellValues(cell,columnIndex))
                        throw new CustomException("Bad cell value in " + columnIndex + " column and " + rowIndex + " row.");
                    columnIndex++;

                }
            }
        }

    }



    @Override
    public boolean checkCellValues(Cell cell, Integer columnIndex) {

        try
        {
            String cellValue = new DataFormatter().formatCellValue(cell);
            switch(columnIndex) {

                case 0: //system - must me string
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING && !cellValue.isEmpty() && cellValue.length() > 1)
                        return true;
                    break;
                case 1: // request - integer
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_STRING )
                    {
                       Integer.parseInt(cellValue);
                       return true;
                    }
                    break;
                case 2:    //order number string with /
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING && !cellValue.isEmpty()
                            && cellValue.length()>1 && cellValue.contains("/"))
                            return true;
                    break;
                case 3:  // from date - date
                    if(HSSFDateUtil.isCellDateFormatted(cell))
                        if(validDate(cell.getDateCellValue()))
                            return true;
                case 4: // to date - date
                    if(HSSFDateUtil.isCellDateFormatted(cell))
                        if(validDate(cell.getDateCellValue()))
                            return true;
                    break;
                case 5: // amount - double\
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        Double.parseDouble(cellValue);
                        return true;
                    }
                    break;
                case 6: //amount_type String
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        if(!cellValue.isEmpty() && cellValue.length()>1)
                          return true;
                    }
                    break;
                case 7: //amount period string
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        if(!cellValue.isEmpty() && cellValue.length()>1)
                            return true;
                    }
                    break;
                case 8: //authoriation percent - double
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        Double.parseDouble(cellValue);
                            return true;
                    }

                    break;
                case 9: //active - boolean true or false
                    if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN || cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        Boolean.parseBoolean(cellValue);
                        return true;
                    }
                    break;
            }
        }
        catch (NumberFormatException  e)
        {
            return false;
        }
        return false;
    }

    @Override
    public boolean validDate(java.util.Date date1) {
            String date;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            date = dateFormat.format(date1);
            if (date.trim().length() != dateFormat.toPattern().length() )
                return false;
            dateFormat.setLenient(false);
            try
            {
                dateFormat.parse(date.trim());
            }
            catch (ParseException e)
            {
                return false;
            }
            return true;
    }
}
