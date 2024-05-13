// package com.example.demo.config;

// import java.io.IOException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.SecurityConfigurer;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.DefaultSecurityFilterChain;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import jakarta.servlet.Filter;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;


// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//     @Autowired
//     private JwtAuthenticationEntryPoint  jwtAuthenticationEntryPoint;
//     @Autowired
//     private  JwtRequestFilter jwtRequestFilter ;

//     @Autowired
//     private UserDetailsService jwtService;
    
//     // Define user accounts with roles
//     @Autowired
//     private PasswordEncoder passwordEncoder;
//     @Bean
//     public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//         return new InMemoryUserDetailsManager(
//                 User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build(),
//                 User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build()
//         );
//     }
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity.formLogin();
//         httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
//         httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
//         httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
//         httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
//         return httpSecurity.build();
//     }
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public  PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();}

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//         authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
//     }

//     // @Bean
//     // public HttpSecurity httpSecurity() throws Exception {
//     //     return httpSecurity();
//     // }

// }
