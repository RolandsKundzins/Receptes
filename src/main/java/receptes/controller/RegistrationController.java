package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import receptes.model.UserModel;
import receptes.type.UserType;

@RestController
public class RegistrationController {
	@Autowired
	private UserModel userModel;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> createUser(@RequestBody UserType user) {
		System.out.println("createUser(" + user.toString() + ')');
		user.setParole(passwordEncoder.encode(user.getParole()));
		Boolean userInserted =  userModel.insertUser(user.getEpasts(), user.getParole(), user.getLietotajvards());
		
		if(userInserted) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.badRequest().body(false);
		}
	}

}
