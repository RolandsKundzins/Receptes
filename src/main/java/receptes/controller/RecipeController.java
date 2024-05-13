//Recipe endpoints


package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import receptes.model.RecipeModel;
import receptes.model.ProductModel;


@SuppressWarnings("unused")
@Controller
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeModel recipeModel;
	
	@Autowired
	private ProductModel productModel;
	

	@GetMapping("/list")
	public String showrecipeModel(Model model) {
        // Load recipe data and add it to the model
		System.out.println("showrecipeModel");
        model.addAttribute("recipes", recipeModel.getAllRecipes());
        return "recipe-list"; // src/main/templates/recipe-list.html
    }
	
	@GetMapping("/object")
	public String showRecipeSingle(@RequestParam("recepteId") int recepteID, Model model) {
		System.out.println(String.format("showRecipeSingle(recepteId: %s))", recepteID));
        model.addAttribute("recepte", recipeModel.getRecipeById(recepteID));
        model.addAttribute("produkti", productModel.getProductsByRecipeId(recepteID));
        return "recipe-single"; // src/main/templates/recipe-single.html
    }
}
