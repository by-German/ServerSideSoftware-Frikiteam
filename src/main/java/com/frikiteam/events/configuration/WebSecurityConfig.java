package com.frikiteam.events.configuration;

import com.frikiteam.events.domain.service.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DefaultUserDetailsService userDetailsService;

    @Autowired
    public DefaultAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private DefaultRequestFilter requestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // indicates than service to use and password encoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        String[] permits = new String[] {
                "/api/auth/*",
                "/api/**",
                "/**" /*all methods permits*/
        };

        String[] denied = new String[] {
                "/api/organizers/*/events",         // create event
                "/api/organizers/*/events/*",       // update event
                "/api/events/*/users/*/comments",   // create comment
                "/api/customers/*/organizers/*",    // follow organizers
                "/api/customers/*/events/*"        // follow events
        };

        // .authorizeRequests()
        // .antMatchers("/*").permitAll() // routes permits
        // .anyRequest().authenticated().and()

        //  inverse
        // .authorizeRequests()
        // .antMatchers("/*").authenticated() // routes denied
        // .anyRequest().permitAll().and()

        httpSecurity.csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers(denied).authenticated()
                .anyRequest().permitAll().and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(requestFilter,
                UsernamePasswordAuthenticationFilter.class);
    }
}
