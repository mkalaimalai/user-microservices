package com.mkalaimalai.controller;

import com.mkalaimalai.domain.User;
import com.mkalaimalai.exception.ResourceNotFoundException;
import com.mkalaimalai.service.UserService;
import com.mkalaimalai.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by kalaimam on 7/14/17.
 */

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserVO createUser(@RequestBody @Valid UserVO user) {
        logger.debug("creating user with email = {}", user.getEmail());
        UserVO updatedUserVO =  userService.createUser(user);
        logger.debug("user created with id  {}", updatedUserVO.getId());
        return updatedUserVO;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserVO getUser(@PathVariable("id") long id) {
        logger.info("getting user with id {}", id);
        UserVO user = userService.findUserById(id);
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<UserVO> findAll() {
        logger.info("getting all the users");
        List<UserVO> users = userService.findAll();
        return users;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public UserVO updateUser(@PathVariable("id") long id, @RequestBody @Valid UserVO user) {
        logger.info("updating User with id {}", id);
        UserVO updatedUserVO = userService.updateUser(id, user);
        logger.info("updated user with id {}", id);
        return updatedUserVO;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") long id) {
        logger.info("fetching & deleting User with id {}", id);
        userService.deleteUser(id);
        logger.info("deleted user with id {}", id);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllUsers() {
        logger.info("deleting all users");
        userService.deleteAllUsers();
        logger.info("deleted all users");

    }

}
