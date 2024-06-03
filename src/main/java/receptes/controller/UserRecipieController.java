package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import receptes.model.UserRecipeModel;

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
