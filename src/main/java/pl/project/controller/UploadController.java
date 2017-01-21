package pl.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;
import pl.project.domain.System;
import pl.project.domain.SystemContract;
import pl.project.exception.CustomException;
import pl.project.service.*;

import java.util.List;

/**
 * Created by Tom on 17.01.2017.
 */
@Controller
public class UploadController {

    @Autowired
    ImportServiceImpl importService;


    @Autowired
    SystemService systemService;

    @Autowired
    SystemContractServiceImpl systemContractService;

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {

        if (file.isEmpty())
            throw new CustomException("Select a file");
        if (!file.getOriginalFilename().endsWith("xls") && !file.getOriginalFilename().endsWith("xlsx"))
            throw new CustomException("Bad file extenstion. Required xls or xlsx.");
        if (file.getBytes() == null)
            throw new CustomException("Empty file.");

        importService.readExcelFile(file);
        List<System> systemList = importService.getSystemList();
        List<SystemContract> systemContractList = importService.getSystemContractList();
        for(System system: systemList)
        {
           if(systemService.getSystemByName(system.getName())==null)
                 systemService.addSystem(system);
        }
        for(SystemContract systemContract: systemContractList)
        {
               systemContractService.addSystemContract(systemContract);
        }

        return "redirect:/" ;

    }



    @RequestMapping(value= "/systems/add", method = RequestMethod.POST)
    public String addSystem(@ModelAttribute("systemContract") SystemContract p){

        if(systemService.getSystemByName(p.getSystem().getName())==null)
            systemService.addSystem(p.getSystem());
        if(p.getId() == null || p.getId() == 0 ){
            this.systemContractService.addSystemContract(p);
        }else{
            //existing person, call update
            this.systemContractService.updateSystemContract(p);
        }

        return "redirect:/";

    }

    @RequestMapping("/remove/{id}")
    public String removeSystem(@PathVariable("id") int id){

        this.systemContractService.deleteSystemContract(id);
        return "redirect:/";
    }


    @RequestMapping("/edit/{id}")
    public String editSystem(@PathVariable("id") int id, Model model){
        model.addAttribute("systemContract", this.systemContractService.getSystemContract(id));
        model.addAttribute("listSystems", this.systemContractService.getAllSystemContracts());
        return "redirect:/";
    }




    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String nfeHandler(Exception e)
    {
        return e.getMessage();
    }

}
