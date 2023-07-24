package com.example.Biblioteka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Biblioteka.service.serviceInterface.MyUserDetailsServiceImpl;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/home").hasAnyRole("ADMIN","EMPLOYEE","USER")
			.antMatchers("/resignation").hasAnyRole("ADMIN","EMPLOYEE","USER")
			.antMatchers("/relase").hasAnyRole("ADMIN","EMPLOYEE")
			.antMatchers("/giveBack").hasAnyRole("ADMIN","EMPLOYEE")
			.and().formLogin().loginPage("/login")
			.defaultSuccessUrl("/home")
			.and().logout().logoutSuccessUrl("/login")
            .permitAll()
            .and().exceptionHandling().accessDeniedPage("/404");
	}
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public MyUserDetailsServiceImpl UsersDetailsService() {
        return new MyUserDetailsServiceImpl();
    }
}
