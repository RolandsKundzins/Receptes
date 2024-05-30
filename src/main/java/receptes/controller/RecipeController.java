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

import receptes.enums.RecipeOrderBy;
import receptes.model.RecipeModel;
import receptes.model.StatisticsModel;
import receptes.type.StatisticsType;
import receptes.model.ProductModel;
import receptes.model.FoodCategoryModel;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeModel recipeModel;
	
	@Autowired
	private ProductModel productModel;
	
	@Autowired
	private FoodCategoryModel foodCategoryModel;
	
	@Autowired
	private StatisticsModel statisticsModel;
	
	@GetMapping("/list")
	public String showRecipeList(Model model, 
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "orderBy", required = false) RecipeOrderBy orderBy) {
        
		// Load recipe data and add it to the model
		System.out.println("showRecipeList");
		model.addAttribute("recipes", recipeModel.getRecipes(search, orderBy));
        
        return "recipe/list"; // src/main/templates/recipe/list.html
    }
	
	@GetMapping("/object")
	public String showRecipeSingle(@RequestParam("recepteID") int recepteID, Model model) {
		System.out.println(String.format("showRecipeSingle(recepteID: %s))", recepteID));
        //Datu ieguve paradisanai
		model.addAttribute("recepte", recipeModel.getRecipeById(recepteID));
        model.addAttribute("produkti", productModel.getProductsByRecipeId(recepteID));
        model.addAttribute("kategorija", foodCategoryModel.getFoodCategoryByRecipeId(recepteID));
        
        //Statistikas saglabasana
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String lietotajvardsSkatitajs = authentication.getName();       
        StatisticsType statistics = new StatisticsType(-1, lietotajvardsSkatitajs, recepteID, null);
        statisticsModel.insertStatistics(statistics);

        return "recipe/single"; // src/main/templates/recipe/single.html //Izmanto model, lai paraditu skata objektus
    }
}
