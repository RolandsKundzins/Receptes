//Recipe endpoints


package com.example.receptes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class RecipeController {

	@Autowired
	private RecipeList recipeList;
	

	@GetMapping("/") //lapa pieejama zem localhost:8080
	public String showRecipeList(Model model) {
        // Load recipe data and add it to the model
		System.out.println("showRecipeList");
        model.addAttribute("recipes", recipeList.getAllRecipes()); //izmantojot 'recipes' parametru, vares izmantot datus
        return "recipe-list"; // So lapu var atrast ieks src/main/templates/recipe-list.html
    }

}
