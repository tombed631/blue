package pl.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.project.domain.System;
import pl.project.domain.SystemContract;
import pl.project.service.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Tom on 17.01.2017.
 */

/**
 * Kontroler zarządzający uploadem
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
    public RedirectView upload(@RequestParam("file") MultipartFile file,RedirectAttributes attributes) throws Exception {

        HashMap<String,String> hashMap = new HashMap<>();

        //jezeli plik jest pusty, zwróć bład poprzez wiadomość
        if (file.isEmpty())
        {
            hashMap.put("error","Select a file");
            attributes.addFlashAttribute("message",hashMap);
            return new RedirectView("/done");
        }
        //jeżeli ma inne rozszrzenie, zwroć błąd poprzez wiadomość
        if (!file.getOriginalFilename().endsWith("xls") && !file.getOriginalFilename().endsWith("xlsx"))
        {
                hashMap.put("error","Bad file extenstion. Required xls or xlsx");
                attributes.addFlashAttribute("message",hashMap);
               return new RedirectView("/done");
        }
        // jeżeli jest pusty w środku zwróc bład poprzez wiadomość
        if (file.getBytes() == null)
        {
            hashMap.put("error","File s empty or blank");
            attributes.addFlashAttribute("message",hashMap);
            return new RedirectView("/done");
        }
        //wywołanie procedury odpowiedzialnej za wczytanie pliku excel

        HashMap<String,String> returnMessageFromImportMap = importService.readExcelFile(file);

        //jezeli w imporcie wystapił jakiś błąd
        if(returnMessageFromImportMap.get("error")!=null)
        {
            attributes.addFlashAttribute("message",returnMessageFromImportMap);
            return new RedirectView("/done");
        }
        //jeżeli import został wykonany poprawnie dalej

        List<System> systemList = importService.getSystemList();
        List<SystemContract> systemContractList = importService.getSystemContractList();

        for(System system: systemList)
        {
            //jezeli systemu nie ma w bazie, dodaj nowy system
            if(systemService.getSystemByName(system.getName())==null)
                systemService.addSystem(system);
        }
        for(SystemContract systemContract: systemContractList)
            systemContractService.addSystemContract(systemContract);

        hashMap.put("success","Sucessfully imported to base.");
        attributes.addFlashAttribute("message",hashMap);
        return new RedirectView("/done");
    }

    /**
     * Wywoływany podczas zakończenia importu
     * @param flashAttribute
     * @param model
     * @return
     */
    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public String importDone(@ModelAttribute("message") HashMap<String,String> flashAttribute,
                              Model model) {

        String success = null;
        String error = null;
        // ustawia parametry dla messega, czy były błędy lub nie
        if(flashAttribute.get("error")!=null && !flashAttribute.get("error").isEmpty())
            error = flashAttribute.get("error");
        else if (flashAttribute.get("success")!=null && !flashAttribute.get("success").isEmpty())
            success = flashAttribute.get("success");

        model.addAttribute("messageError",  error);
        model.addAttribute("messageSuccess",  success);
        model.addAttribute("systemContract",  new SystemContract());
        model.addAttribute("listSystems", systemContractService.getAllSystemContracts());


        return "upload";
    }


    /**
     * Obsługa dodawania nowych systemów do bazy
     * @param p
     * @return
     */
    @RequestMapping(value= "/systems/add", method = RequestMethod.POST)
    public String addSystem(@ModelAttribute("systemContract") SystemContract p){

        if(systemService.getSystemByName(p.getSystem().getName())==null)
            systemService.addSystem(p.getSystem());
        else
            p.setSystem(systemService.getSystemByName(p.getSystem().getName()));

        //jeżeli przekazane ID było wpisane to znaczy, że była edycja, więc leci update
        if(p.getId() == null || p.getId() == 0 ){
            this.systemContractService.addSystemContract(p);
        }else{
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
    public RedirectView editSystem(@PathVariable("id") int id, RedirectAttributes redir){

        redir.addFlashAttribute("systemContract", this.systemContractService.getSystemContract(id));
        return new RedirectView("/");
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String nfeHandler(Exception e)
    {
        return e.getMessage();
    }




}
