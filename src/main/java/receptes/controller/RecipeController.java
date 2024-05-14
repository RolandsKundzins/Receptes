//Recipe endpoints


package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import receptes.model.RecipeModel;
import receptes.model.StatisticsModel;
import receptes.type.StatisticsType;
import receptes.model.ProductModel;


@Controller
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeModel recipeModel;
	
	@Autowired
	private ProductModel productModel;
	
	@Autowired
	private StatisticsModel statisticsModel;
	

	@GetMapping("/list")
	public String showRecipeList(Model model) {
        // Load recipe data and add it to the model
		System.out.println("showrecipeModel");
        model.addAttribute("recipes", recipeModel.getAllRecipes());
        return "recipe-list"; // src/main/templates/recipe-list.html
    }
	
	@GetMapping("/object")
	public String showRecipeSingle(@RequestParam("recepteId") int recepteID, Model model) {
		System.out.println(String.format("showRecipeSingle(recepteId: %s))", recepteID));
        //Datu ieguve paradisanai
		model.addAttribute("recepte", recipeModel.getRecipeById(recepteID));
        model.addAttribute("produkti", productModel.getProductsByRecipeId(recepteID));
        
        //Statistikas saglabasana
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String lietotajvardsSkatitajs = authentication.getName();
        StatisticsType statistics = new StatisticsType(-1, lietotajvardsSkatitajs, recepteID, null);
        statisticsModel.insertStatistics(statistics);

        return "recipe-single"; // src/main/templates/recipe-single.html //Izmanto model, lai paraditu skata objektus
    }
}
