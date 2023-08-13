package ru.skypro.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.impl.AuthService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AuthService.class})
@ExtendWith(SpringExtension.class)
public class AuthServiceTest {
    @Autowired
    private AuthService service;
    @MockBean
    private UserDetailsManager manager;
    @MockBean
    private PasswordEncoder encoder;
    @MockBean
    private UserService userService;

    @Test
    void login_success(){
        when(manager.userExists(any())).thenReturn(true);
        when(manager.loadUserByUsername(any())).thenReturn(User.builder()
                .username("EMAIL@gmail.com")
                .password("PASSWORD")
                .roles(Role.ADMIN.name())
                .build());
        when(encoder.matches(any(),any())).thenReturn(true);
        Assertions.assertEquals(service.login("EMAIL@gmail.com","PASSWORD"),true);
        verify(manager,times(1)).userExists(any());
        verify(manager, times(1)).loadUserByUsername(any());
        verify(encoder, times(1)).matches(any(),any());
    }

    @Test
    void register_success(){
        Register register  = new Register();
        register.setPassword("PASSWORD");
        register.setUsername("EMAIL@gmail.com");
        register.setRole(Role.USER);
        register.setPhone("+7 (777) 777-77-77");
        register.setLastName("last");
        register.setFirstName("first");

        when(manager.userExists(any())).thenReturn(false);
        when(encoder.encode(any())).thenReturn("PASSWORD");
        doNothing().when(manager).createUser(any());
        doNothing().when(userService).addInTable(any());

        Assertions.assertTrue(service.register(register));
        verify(manager, times(1)).userExists(any());
        verify(manager, times(1)).createUser(any());
        verify(encoder, times(1)).encode(any());
        verify(userService, times(1)).addInTable(any());


    }


}
