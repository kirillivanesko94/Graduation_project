package ru.skypro.homework.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.homework.dto.Role;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
//    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    }
//    я бы тут назвал белый лист - для всех и лист для Гостей, которые будут записаны в матчеры
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register"
    };

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user =
//                User.builder()
//                        .username("user@gmail.com")
//                        .password("password")
//                        .passwordEncoder(passwordEncoder::encode)
//                        .roles(Role.USER.name())
//                        .build();
//        UserDetails admin =
//                User.builder()
//                        .username("admin@gmail.com")
//                        .password("password")
//                        .passwordEncoder(passwordEncoder::encode)
//                        .roles(Role.ADMIN.name(), Role.USER.name())
//                        .build();
//        UserDetails guest =
//                User.builder()
//                        .username("guest@gmail.com")
//                        .password("password")
//                        .passwordEncoder(passwordEncoder::encode)
//                        .roles("GUEST")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user, admin, guest);
//    }

//    @Bean
//    public JdbcUserDetailsManager users_entity(DataSource dataSource) {
//        UserDetails user =
//                User.builder()
//                        .username("user@gmail.com")
//                        .password("password")
//                        .roles(Role.USER.name())
//                        .build();
//        UserDetails admin =
//                User.builder()
//                        .username("admin@gmail.com")
//                        .password("password")
//                        .roles(Role.ADMIN.name(), Role.USER.name())
//                        .build();
//        UserDetails guest =
//                User.builder()
//                        .username("guest@gmail.com")
//                        .password("password")
//                        .roles("GUEST")
//                        .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
////        users.createUser(user);
////        users.createUser(admin);
////        users.createUser(guest);
//
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        .mvcMatchers("/ads/**", "/users/**")
                                        .authenticated())
                .cors()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    1. Анонимный пользователь может:
//
//    получать список объявлений.
//
//2. Авторизованный пользователь может:
//
//    получать список объявлений,
//    получать одно объявление,
//    создавать объявление,
//    редактировать и удалять свое объявление,
//    получать список комментариев,
//    создавать комментарии,
//    редактировать/удалять свои комментарии.

//    @Override
//    protected void configure (HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .antMatchers("/ads/**").authenticated()
//                .antMatchers("/читать все/**").hasRole("ADMIN")
//                .antMatchers("/только смотреть все АД/**").hasAnyRole("GUEST")
//                .and()
//                .formLogin()
//                .and()
//                .logout().logoutSuccessUrl("/");
//
//
//}
}
