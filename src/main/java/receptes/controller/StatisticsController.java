package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import receptes.model.StatisticsModel;


@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private StatisticsModel statisticsModel;
	
	
	@GetMapping("/single")
	public String showStatisticsSingle(Model model) {
		System.out.println("showStatisticsSingle");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String lietotajvardsSkatitajs = authentication.getName();

      model.addAttribute("recipes", statisticsModel.getStatisticsByLietotajvards(lietotajvardsSkatitajs));
        return "statistics/single"; // src/main/templates/recipe-list.html
    }
}
