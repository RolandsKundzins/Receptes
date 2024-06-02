package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import receptes.model.UserRecipeModel;
import receptes.model.RecipeModel;
import receptes.type.OperationResult;
import receptes.type.RecipeType;

@Controller
@RequestMapping("/user-recipe")
public class UserRecipieController {

	
	@Autowired
	private UserRecipeModel UserRecipeModel;	

	@GetMapping("/list")
	public String showUserRecipies(Model model) {
		model.addAttribute("userRecipies", UserRecipeModel.getUserRecipies());
        return "userRecipe/list"; // src/main/templates/recipe-list.html
    }

	@GetMapping("/list-liked")
	public String showUserLikedRecipies(Model model) {
		model.addAttribute("userRecipies", UserRecipeModel.getUserLikedRecipies());
        return "userRecipe/likedList"; // src/main/templates/recipe-list.html
    }
	
}
