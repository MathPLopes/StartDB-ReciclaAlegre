package db.start.reciclaalegre.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/swagger-ui/**", "/v3/api-docs/**",
                        //        "/swagger-resources/**", "/webjars/**")
                        //.permitAll()
                        //.requestMatchers("/h2-console/**").permitAll()
                        //.requestMatchers("/api/auth").permitAll() //login
                        //.requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll() // cadastro
                        // .requestMatchers("/api/usuarios/**").authenticated()
                        // .requestMatchers(HttpMethod.POST,"/api/solicitacoes").hasRole(TipoUsuario.GERADOR.name())
                        // .requestMatchers("api/solicitacoes/**").authenticated()
                        .anyRequest().permitAll())//authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

   // @Bean
    //public CorsConfigurationSource corsConfigurationSource() {
    //    CorsConfiguration config = new CorsConfiguration();
    //    config.setAllowedOrigins(List.of("http://localhost:5173"));
    //    config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS","HEAD"));
    //    config.setAllowedHeaders(List.of("*"));
    //    config.setAllowCredentials(true);
    //    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //    source.registerCorsConfiguration("/**", config);
    //    return source;
   // }
}
