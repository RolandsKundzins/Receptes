package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import receptes.model.FoodCategoryModel;
import receptes.type.FoodCategoryType;


@SuppressWarnings("unused")
@Controller
@RequestMapping("/food-category")
public class FoodCategoryController {

	@Autowired
	private FoodCategoryModel foodCategoryModel;	

	@GetMapping("/list")
	public String showFoodCategoryModel(Model model) {
        model.addAttribute("foodCategories", foodCategoryModel.getAllFoodCategories());
        return "foodCategory-list"; // src/main/templates/recipe-list.html
    }
	
	//varbut japieliek vienas kategorijas apskate, bet nezinu vai ir vērts, jo listā
	//jau tāpat var visu redzēt
	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		FoodCategoryType foodCategory = new FoodCategoryType();
		model.addAttribute("foodCategory", foodCategory);
        return "foodCategory-create"; 
	}
	
	@PostMapping("/create")
	public String addFoodCategory(@ModelAttribute("foodCategory") FoodCategoryType foodCategory) {
		foodCategoryModel.addFoodCategory(foodCategory);
	    return "redirect:/food-category/list";
	}
	
	@GetMapping("/edit")
	public String showEditPage(@RequestParam("foodCategoryId") int foodCategoryId, Model model) {
        model.addAttribute("foodCategory", foodCategoryModel.getFoodCategoryById(foodCategoryId));
        return "foodCategory-edit"; 
    }
	
	@PostMapping("/update")
	public String updateFoodCategory(@ModelAttribute("foodCategory") FoodCategoryType foodCategory) {
		foodCategoryModel.updateFoodCategory(foodCategory);
	    return "redirect:/food-category/list";
	}
	
	@GetMapping("/delete")
	public String deleteFoodCategory(@RequestParam("foodCategoryId") int foodCategoryId) {
		foodCategoryModel.deleteFoodCategory(foodCategoryId);
	    return "redirect:/food-category/list"; 
	}
	
}
