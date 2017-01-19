package pl.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;
import pl.project.domain.System;
import pl.project.domain.SystemContract;
import pl.project.exception.CustomException;
import pl.project.service.ImportService;
import pl.project.service.SystemContractService;
import pl.project.service.SystemContractServiceImpl;
import pl.project.service.SystemServiceImpl;

import java.util.List;

/**
 * Created by Tom on 17.01.2017.
 */
@RestController
public class UploadController {

    @Autowired
    ImportService importService;


    @Autowired
    SystemServiceImpl systemService;

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {

        if (file.isEmpty())
            throw new CustomException("Select a file");
        if (!file.getOriginalFilename().endsWith("xls") && !file.getOriginalFilename().endsWith("xlsx"))
            throw new CustomException("Bad file extenstion. Required xls or xlsx.");
        if (file.getBytes() == null)
            throw new CustomException("Empty file.");

         importService.readExcelFile(file);
         System system = systemService.getSystem(1);
         List<System> s = systemService.getAllSystems();
         return "file name:" + file.getOriginalFilename() + " content:" ;

    }

    @ExceptionHandler(value = Exception.class)
    public String nfeHandler(Exception e){
        return e.getMessage();
    }

}
