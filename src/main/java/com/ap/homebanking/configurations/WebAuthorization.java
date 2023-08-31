package com.ap.homebanking.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

                .antMatchers(HttpMethod.POST, "/api/login", "/api/logout","/api/clients").permitAll()

                .antMatchers("/web/index.html","/web/js/index.js","/web/img/favicon.ico","/web/img/mindhub.jpg", "/web/img/Mindhub-logo.png","/web/css/style.css").permitAll()

                .antMatchers("/api/clients/current/cards", "/api/clients/current").hasAnyAuthority("CLIENT", "ADMIN")

                .antMatchers(HttpMethod.POST, "/api/clients/current/accounts", "/api/clients/current/cards", "/api/transfers").hasAnyAuthority("CLIENT", "ADMIN")

                .antMatchers(HttpMethod.GET, "/api/clients/current/cards").hasAnyAuthority("CLIENT", "ADMIN")

                .antMatchers("/web/accounts.html", "/web/account.html","/web/cards.html", "/web/create-cards.html", "/web/transfers.html").hasAnyAuthority("CLIENT", "ADMIN")

                .antMatchers("/web/js/accounts.js", "/web/js/account.js","/web/js/cards.js", "/web/js/create-cards.js", "/web/js/transfers.js").hasAnyAuthority("CLIENT", "ADMIN")

                .antMatchers("/web/css/cards.css").hasAnyAuthority("CLIENT", "ADMIN")

                .antMatchers(HttpMethod.GET,"/api/clients").hasAuthority("ADMIN")

                .antMatchers("/rest/**", "/h2-console/**").hasAuthority("ADMIN")

//                .anyRequest().denyAll()
        ;

        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");


            http.csrf().disable();
            http.logout().logoutUrl("/api/logout");
            http.headers().frameOptions().disable();
            http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
            http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));
            http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
            http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

    }


