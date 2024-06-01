//Recipe endpoints


package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import receptes.enums.RecipeOrderBy;
import receptes.model.RecipeModel;
import receptes.model.StatisticsModel;
import receptes.type.RecipeLikeType;
import receptes.type.RecipeType;
import receptes.type.StatisticsType;
import receptes.model.ProductModel;
import receptes.model.RecipeLikeModel;
import receptes.model.FoodCategoryModel;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeModel recipeModel;
	
	@Autowired
	private RecipeLikeModel recipeLikeModel;
	
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
		model.addAttribute("recipes", recipeModel.getListOfRecipes(search, orderBy));
        
        return "recipe/list"; // src/main/templates/recipe/list.html
    }
	
	@GetMapping("/object")
	public String showRecipeSingle(@RequestParam("recepteID") int recepteID, Model model) {
		System.out.println(String.format("showRecipeSingle(recepteID: %s))", recepteID));
		String lietotajvardsSkatitajs = SecurityContextHolder.getContext().getAuthentication().getName();

        //Receptes datu ieguve parādīšanai
		RecipeType recipe = recipeModel.getRecipeById(recepteID);
		model.addAttribute("recepte", recipe);
        model.addAttribute("produkti", productModel.getProductsByRecipeId(recepteID));
        model.addAttribute("kategorija", foodCategoryModel.getFoodCategoryByRecipeId(recepteID));
        model.addAttribute("lietotajvardsSkatitajs", lietotajvardsSkatitajs);
        
        RecipeLikeType recipeLikeType = new RecipeLikeType(
    		recipeLikeModel.isRecipeAlreadyLiked(lietotajvardsSkatitajs, recepteID), 
    		recipeLikeModel.recipeLikeCount(recepteID)
        );
        model.addAttribute("receptePatik", recipeLikeType);
        
        //Statistikas saglabasana
        if(lietotajvardsSkatitajs.equals(recipe.getLietotajvards())) {
        	System.out.println("Lietotajs apskata savu recepti. Statistika nemainās.");
        } else {
        	StatisticsType statistics = new StatisticsType(lietotajvardsSkatitajs, recepteID);
        	statisticsModel.insertRecipeView(statistics);
        }

        return "recipe/single"; // src/main/templates/recipe/single.html
    }
}
