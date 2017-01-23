package pl.project.service;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.project.domain.System;
import pl.project.domain.SystemContract;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Tom on 17.01.2017.
 */
@Service
@Transactional
public class ImportServiceImpl implements ImportService {


    private System system;
    private SystemContract systemContract;

    private ArrayList<System> systemList;
    private ArrayList<SystemContract> systemContractList;


    @Autowired
    SystemServiceImpl systemService;


    /**
     * Obsługa czytania excela, zwraca hasmape z wiadomością, 'obsługa wyjatkow' poprzez
     * odpowiednie wiadomosci
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public HashMap<String,String> readExcelFile(MultipartFile file) throws Exception {
        HashMap<String,String> toReturn = new HashMap<>();
        Workbook workbook;
        ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
        if (file.getOriginalFilename().endsWith("xls"))
            workbook = new HSSFWorkbook(bis);
        else
            workbook = new XSSFWorkbook(bis);

        Sheet sheet = workbook.getSheetAt(0);
        Integer rowIndex = 0;

        systemContractList = new ArrayList<>();
        systemList = new ArrayList<>();



        for (Row row : sheet) {

            system = new System();
            systemContract = new SystemContract();

            rowIndex = row.getRowNum()+1;
            if(row.getRowNum() == 0 && !row.getCell(0).getStringCellValue().equalsIgnoreCase("system"))
            {
                toReturn.put("error","Bad content of file.");
                return toReturn;
            }
            if (row.getRowNum() != 0) {
                Iterator<Cell> cellIterator = row.cellIterator();
                Integer columnIndex = 0;
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    if(!checkCellValues(cell,columnIndex))
                    {
                        toReturn.put("error","Bad cell value in " + columnIndex + " column and " + rowIndex + " row.");
                        return toReturn;
                    }
                    columnIndex++;
                }
                if(systemService.getSystemByName(system.getName())!=null)
                    system = systemService.getSystemByName(system.getName());
                systemContract.setSystem(system);
                systemContractList.add(systemContract);
                systemList.add(system);
            }
        }

        if(systemList.size()==0 || systemContractList.size()==0)
        {
            toReturn.put("error","File s empty or blank.");
            return toReturn;
        }
        toReturn.put("success","true");
        return toReturn;
    }


    /**
     * Sprawdza kolumny w excelu, czy są poprawne oraz czy są dobrego typu
     * @param cell
     * @param columnIndex
     * @return
     */
    @Override
    public boolean checkCellValues(Cell cell, Integer columnIndex) {

        try
        {
            String cellValue = new DataFormatter().formatCellValue(cell);
            switch(columnIndex) {

                case 0: //system - must me string
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING && !cellValue.isEmpty() && cellValue.length() > 1)
                    {
                        system.setName(cellValue);
                        return true;
                    }
                    break;
                case 1: // request
                    if (!cellValue.isEmpty() && cellValue.length()>1)
                    {
                       systemContract.setRequest(cellValue);
                       return true;
                    }
                    break;
                case 2:    //order number string with /
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING && !cellValue.isEmpty()
                            && cellValue.length()>1 && cellValue.contains("/"))
                    {
                        systemContract.setOrderNumber(cellValue);
                        return true;
                    }
                    break;
                case 3:  // from date - date
                    if(HSSFDateUtil.isCellDateFormatted(cell))
                        if(validDate(cell.getDateCellValue()))
                        {
                            systemContract.setFromDate(new java.sql.Date(cell.getDateCellValue().getTime()));
                            return true;
                        }

                case 4: // to date - date
                    if(HSSFDateUtil.isCellDateFormatted(cell))
                        if(validDate(cell.getDateCellValue()))
                            if(validDate(cell.getDateCellValue()))
                            {
                                systemContract.setToDate(new java.sql.Date(cell.getDateCellValue().getTime()));
                                return true;
                            }
                    break;
                case 5: // amount - double\
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_STRING )
                    {
                        Double d = Double.parseDouble(cellValue);
                        systemContract.setAmount(d);
                        return true;
                    }
                    break;
                case 6: //amount_type String
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        if(!cellValue.isEmpty() && cellValue.length()>1)
                        {
                            systemContract.setAmountType(cellValue);
                            return true;
                        }

                    }
                    break;
                case 7: //amount period string
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        if(!cellValue.isEmpty() && cellValue.length()>1)
                        {
                            systemContract.setAmountPeriod(cellValue);
                            return true;
                        }
                    }
                    break;
                case 8: //authoriation percent - double
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        Double d = Double.parseDouble(cellValue);
                        systemContract.setAuthorizationPercent(d);
                        return true;
                    }

                    break;
                case 9: //active - boolean true or false
                    if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN || cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        Boolean b = Boolean.parseBoolean(cellValue);
                        systemContract.setActive(b);
                        return true;
                    }
                    break;
            }
        }
        catch (NumberFormatException  e)
        {
            return false;
        }
        catch (Exception e)
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

    public ArrayList<System> getSystemList() {
        return systemList;
    }

    public ArrayList<SystemContract> getSystemContractList() {
        return systemContractList;
    }




}
