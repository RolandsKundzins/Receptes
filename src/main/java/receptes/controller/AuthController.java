package receptes.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
		return "login"; // src/main/templates/login.html
    }



	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
		System.out.println("showRegistrationForm");
        model.addAttribute("user", new UserType());
        return "registration"; // src/main/templates/registration.html
    }
	
	
	//Apstrada datus no registracijas lapas
	@PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserType user, RedirectAttributes redirectAttributes, HttpSession session) {
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
