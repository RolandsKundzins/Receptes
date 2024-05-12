package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import receptes.model.RecipeModel;


@SuppressWarnings("unused")
@Controller
public class HomeController {
	
	@Autowired
	private RecipeModel recipeModel;

	@GetMapping("/home")
	public String Home(Model model) {

		System.out.println("Home lapa");
		// pagaidām home lapa iegūst visas receptes, bet vajadzētu kaut kādas apmēram random populārākās
        model.addAttribute("recipes", recipeModel.getAllRecipes());
        return "home"; 
    }
	
	@GetMapping("/home/test")
	public String test() {

        return "test"; 
    }
	
}
