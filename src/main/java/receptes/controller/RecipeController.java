//Recipe endpoints


package receptes.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import receptes.enums.RecipeOrderBy;
import receptes.model.RecipeModel;
import receptes.model.UserModel;
import receptes.type.ProductRecipeType;
import receptes.type.ProductType;
import receptes.type.RecipeLikeType;
import receptes.type.RecipeType;
import receptes.type.RecipeViewType;
import receptes.model.ProductModel;
import receptes.model.ProductRecipeModel;
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
	private UserModel userModel;
	
	@Autowired
	private ProductRecipeModel productRecipeModel;
	
	
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
    		recipeLikeModel.getRecipeLikeCount(recepteID)
        );
        model.addAttribute("receptePatik", recipeLikeType);
        
        //Statistikas saglabasana
        if(lietotajvardsSkatitajs.equals(recipe.getLietotajvards())) {
        	System.out.println("Lietotajs apskata savu recepti. Statistika nemainās.");
        } else {
        	RecipeViewType statistics = new RecipeViewType(lietotajvardsSkatitajs, recepteID);
        	recipeModel.insertRecipeUserViewed(statistics);
        }

        return "recipe/single"; // src/main/templates/recipe/single.html
    }
	
	@GetMapping("/create")
	public String showCreateRecipe(Model model) {
		model.addAttribute("foodCategories", foodCategoryModel.getAllFoodCategories());
		model.addAttribute("products", productModel.getAllProducts());
		return "recipe/create";
	}
	
	@GetMapping("/editobject")
	public String showEditRecipe(Model model,
			@RequestParam("recepteID") int recepteID) {
		
		String lietotajvardsSkatitajs = SecurityContextHolder.getContext().getAuthentication().getName();

		RecipeType recipe = recipeModel.getRecipeById(recepteID);
		model.addAttribute("recepte", recipe);
        model.addAttribute("lietotajvardsSkatitajs", lietotajvardsSkatitajs);
		model.addAttribute("foodCategories", foodCategoryModel.getAllFoodCategories());
		model.addAttribute("products", productModel.getAllProducts());
		
		List<ProductType> selectedProducts = productModel.getProductsByRecipeId(recepteID);
		
		List<Integer> selectedProductIds = selectedProducts.stream()
                .map(ProductType::getProduktsID)
                .collect(Collectors.toList());
		
		model.addAttribute("selectedProductIds", selectedProductIds);
		
		return "recipe/edit";
	}
	
	@PostMapping("/save")
	public String saveRecipe(
	        @RequestParam("recipe-name") String recipeName,
	        @RequestParam("recipe-cooking-time") int recipeCookingTime,
	        @RequestParam("recipe-description") String recipeDescription,
	        @RequestParam("selected-category-id") int categoryId,
	        @RequestParam("selected-products") String products,
	        RedirectAttributes redirectAttributes) {
		
		System.out.println(recipeName);
		System.out.println(recipeCookingTime);
		System.out.println(recipeDescription);
		System.out.println(categoryId);
		System.out.println(products);
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int userId = userModel.findByUsername(username).getLietotajsID();
		
	    RecipeType recipeType = new RecipeType(
	    		recipeName, 
	    		recipeCookingTime,
	    		recipeDescription, 
	    		userId, 
	    		categoryId);

	    int recipeId = recipeModel.InsertRecipe(recipeType);
	    
	    String[] productArray = products.split(",");
	    int[] selectedProducts = Arrays.stream(productArray).mapToInt(Integer::parseInt).toArray();
	    System.out.println(selectedProducts);
	    
	    //insert to produktsrecepte
	    for (int productId : selectedProducts) {
	    	ProductRecipeType productRecipeType = new ProductRecipeType(recipeId, productId);
	    	productRecipeModel.InsertProductRecipe(productRecipeType);
	    }
	    

	    redirectAttributes.addFlashAttribute("message", "Recepte ir veiksmigi pievienota!");

	    // Redirect to recipe list
	    return "redirect:/recipe/list";
	}
	
	@PostMapping("/update")
	public String updateRecipe(
			@RequestParam("recipe-id") int recipeId,
	        @RequestParam("recipe-name") String recipeName,
	        @RequestParam("recipe-cooking-time") int recipeCookingTime,
	        @RequestParam("recipe-description") String recipeDescription,
	        @RequestParam("selected-category-id") int categoryId,
	        @RequestParam("selected-products") String products,
	        RedirectAttributes redirectAttributes) {
		
		System.out.println("updateRecipe");
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int userId = userModel.findByUsername(username).getLietotajsID();
		
	    RecipeType recipeType = new RecipeType(
	    		recipeId,
	    		recipeName, 
	    		recipeCookingTime,
	    		recipeDescription, 
	    		userId, 
	    		categoryId);

	    recipeModel.UpdateRecipe(recipeType);
	    
	    String[] productArray = products.split(",");
	    int[] selectedProducts = Arrays.stream(productArray).mapToInt(Integer::parseInt).toArray();
	    
	    productRecipeModel.DeleteByRecipeId(recipeId);
	    
	    //insert to produktsrecepte
	    for (int productId : selectedProducts) {
	    	ProductRecipeType productRecipeType = new ProductRecipeType(recipeId, productId);
	    	productRecipeModel.InsertProductRecipe(productRecipeType);
	    }
	    

	    redirectAttributes.addFlashAttribute("message", "Recepte ir veiksmigi redigeta!");

	    // Redirect to recipe list
	    return "redirect:/recipe/object?recepteID=" + recipeId;
	}

	
}
