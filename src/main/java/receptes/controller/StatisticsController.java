package receptes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/statistics")
public class StatisticsController {
//	@Autowired
//	private StatisticsModel statisticsModel;
	
	
	@GetMapping("/single")
	public String showStatisticsSingle(Model model) {
		System.out.println("showStatisticsSingle");
//		Jaiegust lietotaja id/lietotajvards - piemers recipeController
//      model.addAttribute("recipes", statisticsModel.getStatisticById());
        return "statistics-single"; // src/main/templates/recipe-list.html
    }
}
