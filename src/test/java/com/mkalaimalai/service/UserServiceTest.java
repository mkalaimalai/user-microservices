package com.mkalaimalai.service;

import com.mkalaimalai.domain.Address;
import com.mkalaimalai.domain.AddressType;
import com.mkalaimalai.domain.User;
import com.mkalaimalai.mapper.UserMapper;
import com.mkalaimalai.repository.UserRepository;
import org.assertj.core.api.Java6Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kalaimam on 9/20/17.
 */
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {
            @Bean
        public UserService userService() {
            return new UserService();
        }
    }
    @Before
    public void setUp() {
        User user = createUser();

        Mockito.when(userRepository.findByUserName(user.getUserName()))
                .thenReturn(user);
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "Bret";

        User found = userService.findByUserName(name);

        Java6Assertions.assertThat(found.getUserName())
                .isEqualTo(name);
    }

    private User createUser(){
        User user = new User();
        user.setEmail("Sincere@april.biz");
        user.setUserName("Bret");
        user.setFirstName("Leanne");
        user.setLastName("Graham");
        Address address = new Address();
        address.setLine1("Kulas Light");
        address.setLine2("Apt. 556");
        address.setCity("Gwenborough");
        address.setCountry("US");
        address.setType(AddressType.HOME);
        user.addAddress(address);
        return user;
    }
}
