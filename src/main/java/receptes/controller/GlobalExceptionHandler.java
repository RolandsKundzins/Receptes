package receptes.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//TODO Šis vēl joprojām nestrādā :? Github Projects uzdevums #22

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
    	System.out.println("GlobalExceptionHandler.handleException");

        ex.printStackTrace();

        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }
}
