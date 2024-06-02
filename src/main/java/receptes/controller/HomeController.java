package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import receptes.enums.RecipeOrderBy;
import receptes.model.RecipeModel;


@SuppressWarnings("unused")
@Controller
public class HomeController {
	
	@Autowired
	private RecipeModel recipeModel;

	@GetMapping("/home")
	public String showHomePage(Model model) {
		System.out.println("showHomePage");
        model.addAttribute("recipes", recipeModel.getListOfRecipes("", RecipeOrderBy.DATEADDEDDESC));
        return "home"; 
    }
}
