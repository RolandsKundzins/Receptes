package receptes.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException ex, Model model) {
        return handleExceptionInternal(ex, model);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        return handleExceptionInternal(ex, model);
    }

    private String handleExceptionInternal(Exception ex, Model model) {
        System.out.println("GlobalExceptionHandler.handleExceptionInternal");
        
        //Izdrukā reālo kļūdu, piemēram "Column not found", 
        //tā vietā, lai izdrukātu programmētāja definēto "radās kļūda ievietojot lietotāju".
        Throwable rootCause = ex.getCause();
        if (rootCause != null) {
            System.err.println("Root cause: " + rootCause.getMessage());
        }

        ex.printStackTrace();
        
        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }
}
