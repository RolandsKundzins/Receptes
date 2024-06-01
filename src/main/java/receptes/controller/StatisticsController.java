package receptes.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import receptes.model.StatisticsModel;
import receptes.type.StatisticsByDateType;


@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private StatisticsModel statisticsModel;
	
	
	@GetMapping("/single")
	public String showStatisticsForUser(@RequestParam("dienuskaits") int dienuSkaits, Model model) {
		System.out.println("showStatisticsForUser");
		
		if(dienuSkaits < 1 || dienuSkaits > 90) {
			System.out.println("showStatisticsForUser: Lietotājs ievada nelegālas vērtības, uzstāda dienuSkaits = 7.");
			dienuSkaits = 7;
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String lietotajvardsSkatitajs = authentication.getName();
        
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(dienuSkaits-1); // Aprēķina sākuma dienu kā dienuSkaits dienas atpakaļ
        
        List<StatisticsByDateType> viewCountsPerDay = statisticsModel.getStatisticsPerDate(lietotajvardsSkatitajs, startDate, endDate);
        
        model.addAttribute("statistics", viewCountsPerDay);
        
        model.addAttribute("dienu_skaits", dienuSkaits); //nav tas pats, kas "dienuskaits" @RequestParam
        
        return "statistics/single"; // src/main/templates/statistics/single.html
    }
}
