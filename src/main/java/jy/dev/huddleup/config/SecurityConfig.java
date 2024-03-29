package jy.dev.huddleup.config;

import jy.dev.huddleup.security.jwt.JwtAuthProvider;
import jy.dev.huddleup.security.oauth2.CustomAuthenticationFailureHandler;
import jy.dev.huddleup.security.oauth2.CustomAuthenticationSuccessHandler;
import jy.dev.huddleup.security.oauth2.CustomOAuth2UserService;
import jy.dev.huddleup.security.jwt.FilterSkipMatcher;
import jy.dev.huddleup.security.jwt.HeaderTokenExtractor;
import jy.dev.huddleup.security.jwt.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomOAuth2UserService customOAuth2UserService;
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private HeaderTokenExtractor headerTokenExtractor;
    private JwtAuthProvider jwtAuthProvider;

    @Autowired
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService
            , CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler
            , CustomAuthenticationFailureHandler customAuthenticationFailureHandler
            , HeaderTokenExtractor headerTokenExtractor
            , JwtAuthProvider jwtAuthProvider) {

        this.customOAuth2UserService = customOAuth2UserService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        this.headerTokenExtractor = headerTokenExtractor;
        this.jwtAuthProvider = jwtAuthProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthProvider);
    }

    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        http
                .oauth2Login()
                .defaultSuccessUrl("/api/main")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .userInfoEndpoint()
                .userService(customOAuth2UserService);


    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS", "PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    private JwtAuthFilter jwtFilter() throws Exception {
        List<String> skipPathList = new ArrayList<>();

        // 회원 관리 API 허용
        skipPathList.add("POST,/api/signup");
        skipPathList.add("POST,/api/login");

        skipPathList.add("POST,/login");
        skipPathList.add("GET,/login");

        skipPathList.add("GET,/api/main");
        skipPathList.add("GET,/test");
        skipPathList.add("GET,/");

        FilterSkipMatcher matcher = new FilterSkipMatcher(
                skipPathList,
                "/**"
        );

        JwtAuthFilter filter = new JwtAuthFilter(
                matcher,
                headerTokenExtractor
        );
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }
}











