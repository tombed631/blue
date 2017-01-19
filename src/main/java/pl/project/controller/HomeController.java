package pl.project.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tom on 16.01.2017.
 */
@Controller
@EnableAutoConfiguration
public class HomeController  extends SpringBootServletInitializer {

        @RequestMapping("/")
        public String index() {

            return "upload";
        }

    }