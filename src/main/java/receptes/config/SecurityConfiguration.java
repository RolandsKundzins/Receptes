package receptes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import receptes.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private MyUserDetailsService userDetailsService;

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity
			.csrf(htppSecurityCsrfConfigurer -> htppSecurityCsrfConfigurer.disable())
			.authorizeHttpRequests(registry -> {
				registry.antMatchers("/register").permitAll(); //sadi var iestatit lapas, kuram var pieklut bez auth
				//registry.antMatchers("/admin/**").hasRole("ADMIN"); //pagaidam nav vairaku lietotaja limenu
				//registry.antMatchers("/user/**").hasRole("USER");
				registry.anyRequest().authenticated();
			})
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/receptes", true)
	            .permitAll()
	        )
			.build();
	}
	

	@Bean
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
}
