package pl.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.project.domain.SystemContract;
import pl.project.service.SystemContractServiceImpl;
import pl.project.service.SystemServiceImpl;

/**
 * Created by Tom on 16.01.2017.
 */
@Controller
@EnableAutoConfiguration
public class HomeController  extends SpringBootServletInitializer {

    @Autowired
    SystemContractServiceImpl systemContractService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("systemContract", new SystemContract());
        model.addAttribute("listSystems", systemContractService.getAllSystemContracts());
        return "upload";
    }

    }