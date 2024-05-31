package receptes.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import receptes.model.UserModel;
import receptes.type.OperationResult;
import receptes.type.UserType;

@Controller
public class AuthController {
	@Autowired
	private UserModel userModel;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	//successMessage tiek pievenots, kad tiek veiksmigi izveidots jauns lietotajs
	@GetMapping("/login")
    public String showCustomLoginPage(HttpSession session, Model model) {
		System.out.println("showCustomLoginPage");
		return "auth/login"; // src/main/templates/login.html
    }



	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
		System.out.println("showRegistrationForm");
        model.addAttribute("user", new UserType());
        return "auth/registration"; // src/main/templates/registration.html
    }
	
	
	//Apstrada datus no registracijas lapas
	@PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserType user, RedirectAttributes redirectAttributes, HttpSession session) {
		//Pārbaudes:
		// - Epasts pārbaude
		String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(user.getEpasts());
		if (!matcher.matches()) {
		    return "redirect:/register?error=" + URLEncoder.encode("Nederīgs e-pasta formāts. Lūdzu, ievadiet derīgu e-pasta adresi, piemēram, lietotajs@epasts.lv", StandardCharsets.UTF_8);
		}
		// - Lietotājvārda pārbaude
		if(user.getLietotajvards().length() < 4) {
			return "redirect:/register?error=" + URLEncoder.encode("Lietotājvārda garumam jābūt vismaz 4 simboliem", StandardCharsets.UTF_8);
		}
		// - Paroles pārbaude
		if(user.getParole().length() < 8) {
			return "redirect:/register?error=" + URLEncoder.encode("Parolei jāsatur vismaz 8 cipari", StandardCharsets.UTF_8);
		}
		// - Lietotāja eksistēšanas pārbaude
		String userExists = userModel.checkUserExists(user.getLietotajvards(), user.getEpasts());
		if(!"".equals(userExists)) {
			return "redirect:/register?error=" + URLEncoder.encode(userExists, StandardCharsets.UTF_8);
		}
		
        // Sifre paroli
        String encodedPassword = passwordEncoder.encode(user.getParole());

        // Ievieto datus DB
        OperationResult insertResult = userModel.insertUser(user.getEpasts(), encodedPassword, user.getLietotajvards());

        if (insertResult.isSuccess()) {
            // Novirza lietotaju uz pieslegsanas lapu, ja ir veiksmiga jauna lietotaja registresana
            return "redirect:/register?successMessage=" + URLEncoder.encode("Lietotajs izveidots. Ludzu piesledzies!", StandardCharsets.UTF_8); //nevar isti pievienot parametrus (pazinojumu), jo 
        } else {
            // Novirza atpakal uz jauna lietotaja registresanas lapu, ja ir kluda
            return "redirect:/register?error=" + URLEncoder.encode(insertResult.getMessage(), StandardCharsets.UTF_8);
        }
    }
}
