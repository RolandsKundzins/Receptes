//Recipe endpoints


package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import receptes.model.RecipeList;


@Controller
@RequestMapping("/")
public class RecipeController {

	@Autowired
	private RecipeList recipeList;
	
	
	@GetMapping("/")
    public String redirectToRecipeList(RedirectAttributes attributes) {
        return "redirect:/receptes";
    }
	

	@GetMapping("/receptes")
	public String showRecipeList(Model model) {
        // Load recipe data and add it to the model
		System.out.println("showRecipeList");
        model.addAttribute("recipes", recipeList.getAllRecipes());
        return "recipe-list"; // src/main/templates/recipe-list.html
    }
	
	
	@GetMapping("/recepte")
	public String showRecipeSingle(@RequestParam("recepteId") String recepteId, Model model) {
		// TODO FINISH THIS IN #3 task (Receptes skats)
		System.out.println(String.format("showRecipeSingle(recepteId: %s))", recepteId));
//        model.addAttribute("recipe", recipeList.getRecipe());
        return "recipe-single"; // src/main/templates/recipe-single.html
    }

}
