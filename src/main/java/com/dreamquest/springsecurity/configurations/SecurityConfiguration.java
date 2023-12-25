package com.dreamquest.springsecurity.configurations;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   
	@Bean
    public SecurityFilterChain securityChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((authorize) -> authorize
			
			.requestMatchers("/login").permitAll()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

			
			.anyRequest().authenticated()
		).formLogin(form -> form
						.loginPage("/login").loginProcessingUrl("/login-processing")
//						.defaultSuccessUrl("/reservation-form")
				.permitAll()
			);
    	
    	return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

}
