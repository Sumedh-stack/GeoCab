package com.QuickRide.AuthService.Configuration;


import com.QuickRide.AuthService.services.UserDetailsServiceImplementation;
import com.QuickRide.AuthService.controllers.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SpringSecurity implements WebMvcConfigurer {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailsServiceImplementation userDetailsService;


    @Bean
    AuthenticationFilter authenticationFilter(){
        return new AuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)  // Correct way to disable CSRF
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/v1/auth/signup/*").permitAll()
                                .requestMatchers("/api/v1/auth/signin/*").permitAll()
                        .requestMatchers("/api/v1/auth/validate").authenticated()
                )
         .authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    // https://medium.com/@benaya7/cors-configuration-in-spring-security-and-webmvc-lets-get-it-out-of-the-way-47ba059ca524

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }


//
////    @Bean
////    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
////            AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
////            authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
////            return authenticationManagerBuilder.build();
////        }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//        @Bean
//    public AuthenticationProvider authenticationProvider(){
//            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//            authenticationProvider.setUserDetailsService(new UserDetailsServiceImplementation());
//            authenticationProvider.setPasswordEncoder(passwordEncoder());
//            return authenticationProvider;
//        }

    //
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new UserDetailsServiceImplementation();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
//        http
//            .cors(AbstractHttpConfigurer::disable) // Use CorsConfigurationSource bean
//                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF with new method signature
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set session creation policy to stateless
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.POST, "/api/v1/signup/**").permitAll() // Public endpoint
//                        .requestMatchers(HttpMethod.POST, "/api/v1/login/**").permitAll() // Public endpoint
//                        .requestMatchers(HttpMethod.GET, "/authentication-docs/**").permitAll() // Public endpoint
//                        .anyRequest().authenticated() // All other requests require authentication
//                )
//                .authenticationManager(authenticationManager); // Set the custom authentication manager
//
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//
//
//        return http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.disable())
//                .authorizeHttpRequests(auth ->
//                        auth
//                                .requestMatchers("/api/v1/auth/signup/*").permitAll()
//                                .requestMatchers("/api/v1/auth/signin/*").permitAll()
//                )
//                .build();
//    }


}

