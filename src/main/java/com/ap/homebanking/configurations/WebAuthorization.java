package com.ap.homebanking.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //antmatchers

        http.authorizeRequests()

                .antMatchers("/rest/**").hasAuthority("ADMIN")

                .antMatchers("/web/accounts.html").hasAuthority("CLIENT")

                .antMatchers("/web/account.html").hasAuthority("CLIENT")

                .antMatchers("/web/cards.html").hasAuthority("CLIENT") //acá no deberia acceder solo el admin?? para que sea el client deberian especificarse solo las tarjetas de melba

                .antMatchers("/web/account.html").hasAuthority("CLIENT")

                .antMatchers(HttpMethod.GET, "/api/clients/**").hasAuthority("CLIENT");



        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");


        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        http.csrf().disable();
        http.logout().logoutUrl("/api/logout");


        //disabling frameOptions so h2-console can be accessed

        http.headers(headers -> headers.frameOptions().disable());

        // if user is not authenticated, just send an authentication failure response

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

    }


