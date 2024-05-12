package receptes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

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
				registry.antMatchers("/home").permitAll();
				registry.antMatchers("/register").permitAll(); //sadi var iestatit lapas, kuram var pieklut bez auth
				//registry.antMatchers("/admin/**").hasRole("ADMIN"); //pagaidam nav vairaku lietotaja limenu
				//registry.antMatchers("/user/**").hasRole("USER");
				registry.anyRequest().authenticated();
			})
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/recipe/list", true)//veiksmīgs logins - novirzas uz recepšu sarakstu
	            .permitAll()
	        )
            .exceptionHandling(exceptionHandling ->
            exceptionHandling
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/home"))
            )//iestatu, ka home page ir sākums
		    .logout(logout ->
		        logout
		            .logoutUrl("/logout")
		            .logoutSuccessUrl("/home")//kad izlogojās, novirzas uz home page
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
