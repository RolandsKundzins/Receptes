package receptes.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import receptes.model.StatisticsModel;
import receptes.type.StatisticsByDateType;


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
        
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(6); // Calculate the start date as seven days ago
        
        List<StatisticsByDateType> viewCountsPerDay = statisticsModel.getViewCountsPerDay(lietotajvardsSkatitajs, startDate, endDate);
        
        for (StatisticsByDateType statistics : viewCountsPerDay) { //
            System.out.println("Date: " + statistics.getDatums() + ", View Count: " + statistics.getSkatijumuSkaits()); //
        } //
        
        
        Map<LocalDate, Integer> viewCountMap = viewCountsPerDay.stream()
                .collect(Collectors.toMap(StatisticsByDateType::getDatums, StatisticsByDateType::getSkatijumuSkaits));

     // Fill in missing dates with zero view counts
        List<StatisticsByDateType> completeViewCountsPerDay = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            int viewCount = viewCountMap.getOrDefault(date, 0);
            completeViewCountsPerDay.add(new StatisticsByDateType(date, viewCount));
        }

        // Convert data to arrays
        String[] datesArray = completeViewCountsPerDay.stream()
            .map(stat -> stat.getDatums().toString())
            .toArray(String[]::new);
        int[] viewCountsArray = completeViewCountsPerDay.stream()
            .mapToInt(StatisticsByDateType::getSkatijumuSkaits)
            .toArray();
        
        model.addAttribute("datesArray", datesArray);
        model.addAttribute("viewCountsArray", viewCountsArray);

        return "statistics/single"; // src/main/templates/recipe-list.html
    }
}
