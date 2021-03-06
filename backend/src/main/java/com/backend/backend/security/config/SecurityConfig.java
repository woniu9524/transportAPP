package com.backend.backend.security.config;

import com.backend.backend.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                //????????????????????????????????????
                .antMatchers("/login").anonymous()
                .antMatchers("/register").anonymous()
                .antMatchers("/sendEmail").anonymous()
                //??????????????????
                .antMatchers("/driver/**").hasAuthority("driver")
                .antMatchers("/owner/**").hasAuthority("owner")
                .antMatchers("/cargoOwner/**").hasAuthority("cargoOwner")
                .antMatchers("/","/login","/register","/normal","/sendEmail").permitAll()
                //????????????????????????
                .anyRequest().authenticated();
        /*//??????JWT?????????
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //??????????????????
        http.exceptionHandling()
                //??????????????????
                .authenticationEntryPoint(authenticationEntryPoint)
                //??????????????????
                .accessDeniedHandler(accessDeniedHandler);
        //????????????
        http.cors();*/
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        // allow Swagger URL to be accessed without authentication
        web.ignoring().antMatchers(
                "/swagger-ui.html",
                "/v2/api-docs", // swagger api json
                "/swagger-resources/configuration/ui", // ???????????????????????????
                "/swagger-resources", // ????????????api-docs???URI
                "/swagger-resources/configuration/security", // ????????????
                "/swagger-resources/**",
                //??????????????????????????????swagger???????????????????????????????????????????????????/webjars???????????????????????????????????????????????????????????????(2020-10-23)
                "/webjars/**"
        );
    }
}