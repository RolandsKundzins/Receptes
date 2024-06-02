package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import receptes.model.RecipeLikeModel;
import receptes.model.RecipeModel;
import receptes.type.OperationResult;
import receptes.type.RecipeType;

@Controller
@RequestMapping("/recipeLike")
public class RecipeLikeController {
	@Autowired
	private RecipeLikeModel recipeLikeModel;
	
	@Autowired
	private RecipeModel recipeModel;
	
	
	@PostMapping("change")
	@ResponseBody
	public OperationResult likeUnlikeRecipe(@RequestParam("recepteID") int recepteID) {
		String lietotajvardsPatik = SecurityContextHolder.getContext().getAuthentication().getName();
		OperationResult response = new OperationResult();
		boolean isRecipeLikedByUser = recipeLikeModel.isRecipeAlreadyLiked(lietotajvardsPatik, recepteID);
		
		if (isRecipeLikedByUser) {
			recipeLikeModel.deleteRecipeLike(lietotajvardsPatik, recepteID);
			response.setSuccess(true);
			response.addExtraData("patik", false);
			response.addExtraData("patikSkaits", recipeLikeModel.getRecipeLikeCount(recepteID));
			response.setMessage("Receptei noņemts patīk :/");
	    } else {
	    	//Datu pārbaudes
	    	RecipeType recipe = recipeModel.getRecipeById(recepteID);
	    	if(recipe.getLietotajvards().equals(lietotajvardsPatik)) {
				response.setSuccess(false);
				response.setMessage("Kļūda! Nedrīkst atzīmēt pats savu recepti ar patīk!");
				response.addExtraData("patik", false);
				return response;
			}
			
			//Patīk pievienošana DB
			recipeLikeModel.addRecipeLike(lietotajvardsPatik, recepteID);
		    response.setSuccess(true);
		    response.addExtraData("patik", true);
		    response.addExtraData("patikSkaits", recipeLikeModel.getRecipeLikeCount(recepteID));
		    response.setMessage("Recepte atzīmēta ar patīk :)");
	    }
	    
	    return response;
	}
}
