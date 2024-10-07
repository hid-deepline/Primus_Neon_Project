package fr.doranco.primus_neon.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	SecurityFilterChain configureHttpSecurity(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/images/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/js/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/webjars/**").permitAll());
		http.authenticationProvider(authenticationProvider());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/users/**", "/settings/**").hasAuthority("Admin")
				.requestMatchers("/categories/**", "/brands/**").hasAnyAuthority("Admin", "Editeur")
				.requestMatchers("/products/**").hasAnyAuthority("Admin", "Editeur", "Commercial", "Expéditeur")
				.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").usernameParameter("email").permitAll())
				.logout(logout -> logout.permitAll())
				.rememberMe(rem -> rem
						.key("AbcDefgHijKlmnOpqurs_1234567890")
						.tokenValiditySeconds(7 * 24 * 60 * 60));
		
		return http.build();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new PrimusNeonUserDetailsService();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
 
}
