package pl.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Tom on 17.01.2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler({IOException.class,Exception.class})
        public RedirectView handleBaseException(Exception e, RedirectAttributes redirectAttributes)
        {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("error",e.getMessage());
            redirectAttributes.addFlashAttribute("message",hashMap);
            return new RedirectView("/done");
        }

}

