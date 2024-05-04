//Recipe endpoints


package com.example.receptes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
public class RecipeController {

	@Autowired
	private RecipeList recipeList;
	
	@GetMapping("")
	public String showRecipeList(Model model) {
        // Load recipe data and add it to the model
		System.out.println("showRecipeList");
        model.addAttribute("recipes", recipeList.getAllRecipes());
        return "recipe-list"; // Return the name of the Thymeleaf template
    }

}
