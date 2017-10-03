package com.mkalaimalai.controller;

import com.mkalaimalai.domain.Address;
import com.mkalaimalai.domain.AddressType;
import com.mkalaimalai.domain.User;
import com.mkalaimalai.service.UserService;
import com.mkalaimalai.vo.AddressVO;
import com.mkalaimalai.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kalaimam on 9/20/17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void givenUsers_whenGetUsers_thenReturnJsonArray()
            throws Exception {
        String userName = "Bret";

        given(userService.findAll(new PageRequest(0, 10))).willReturn(createUsers("Bret"));

        mvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content.[0].user_name", is(userName)));
    }



    private Page<UserVO> createUsers(String userName){
        UserVO user = new UserVO();
        user.setEmail("Sincere@april.biz");
        user.setUserName(userName);
        user.setFirstName("Leanne");
        user.setLastName("Graham");
        AddressVO address = new AddressVO();
        address.setLine1("Kulas Light");
        address.setLine2("Apt. 556");
        address.setCity("Gwenborough");
        address.setCountry("US");
        address.setType(AddressType.HOME);
        user.setAddresses(Arrays.asList(address));

        return new PageImpl<UserVO>(Arrays.asList(user), new PageRequest(0, 10), 10 );
    }
}
