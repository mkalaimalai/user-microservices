package com.mkalaimalai.controller;

import com.mkalaimalai.service.UserService;
import com.mkalaimalai.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by kalaimam on 7/14/17.
 */

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserVO createUser(@RequestBody @Valid UserVO user) {
        log.debug("creating user with email = {}", user);
        UserVO updatedUserVO =  userService.createUser(user);
        log.debug("user created with id  {}", updatedUserVO.getId());
        return updatedUserVO;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserVO getUser(@PathVariable("id") long id) {
        log.info("getting user with id {}", id);
        UserVO user = userService.findUserById(id);
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<UserVO> findAll() {
        log.info("getting all the users");
        List<UserVO> users = userService.findAll();
        return users;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public UserVO updateUser(@PathVariable("id") long id, @RequestBody @Valid UserVO user) {
        log.info("updating User with id {}", id);
        UserVO updatedUserVO = userService.updateUser(id, user);
        log.info("updated user with id {}", id);
        return updatedUserVO;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") long id) {
        log.info("fetching & deleting User with id {}", id);
        userService.deleteUser(id);
        log.info("deleted user with id {}", id);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllUsers() {
        log.info("deleting all users");
        userService.deleteAllUsers();
        log.info("deleted all users");

    }

}
