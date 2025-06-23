package com.example.FIS_EmployerService.Config;

import com.example.FIS_EmployerService.Service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig {

    @Autowired

    private MyUserDetailService myUserDetailService;

    @Bean
    public SecurityFilterChain securityFilerChain(HttpSecurity http) throws Exception {
        return http
                .csrf(Customizer->Customizer.disable())
                .authorizeHttpRequests(request->request
                        .requestMatchers("register","/swagger.html","/swagger-ui/**", "/swagger-ui.html", "/docs/**","/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST ,"/employer/saveEmployerInfo").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET ,"/employer/getEmployerInfo/{employee_identification_number}").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationProvider authProvider()
    {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        dao.setUserDetailsService(myUserDetailService);
        return dao;
    }

}
