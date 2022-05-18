package ru.marsel.workbench.config;

import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    protected CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of(
            "http://localhost:4200",
            "http://localhost:8080"));
        config.addAllowedHeader("*");

        Stream.of("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
            .forEach(config::addAllowedMethod);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    protected void configure(HttpSecurity http) throws Exception {
        String[] matchers = {
            "/**"
        };
        http
            .csrf().disable()
            .formLogin().disable()
            .logout().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .requestMatchers()
            .antMatchers(matchers)
            .and()
            .authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .addFilterBefore(corsFilter(), ChannelProcessingFilter.class);
    }
}
