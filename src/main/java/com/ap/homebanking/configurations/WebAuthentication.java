package com.ap.homebanking.configurations;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Rol;
import com.ap.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName -> {

            Client client = clientRepository.findByEmail(inputName); // necesito buscar el email y por eso necesito consultar en el repo
            //creamos un cliente y le asignamos el resultado del findby
            //por eso necesito el findbyemail en mi repo

            //interface que brinda spring security que se va a encargar de manejar los datos brindados por el que esta queriendo loggearse
            //hace referencia al nombre ingresado, en este caso al mail

            if (client != null) {

                if (client.getRol().equals(Rol.CLIENT)) {

                    return new User(client.getEmail(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("CLIENT")); //guardando los roles
                } else {
                    return new User(client.getEmail(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                }
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }

        });

    }

            @Bean ///necesito el bean porque el password encoder lo voy a necesitar en otras clases de mi programa
            public PasswordEncoder passwordEncoder () {
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
            }

        }
