package com.mkalaimalai.controller;

import com.mkalaimalai.service.UserService;
import com.mkalaimalai.vo.UserVO;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by kalaimam on 7/14/17.
 */

@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping( method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @CacheEvict(value="users", key="'all_users'")
    public UserVO createUser(@RequestBody @Valid UserVO user) {
        log.debug("creating user with email = {}", user);
        UserVO userVO =  userService.createUser(user);
        userVO.add(linkTo(UserRestController.class).slash(userVO.getUserId()).withSelfRel());
        log.debug("user created with id  {}", userVO.getUserId());
        return userVO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value="users", key="'user:'+#id")
    public UserVO getUser(@PathVariable("id") Long id) {
        log.info("getting user with id {}", id);
        UserVO userVO = userService.findUserById(id);
        return userVO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    @CachePut(value="users", key="'user:'+#id")
    @CacheEvict(value="users", key="'all_users'")
    public UserVO updateUser(@PathVariable("id") long id, @RequestBody @Valid UserVO user) {
        log.info("updating User with id {}", id);
        UserVO updatedUserVO = userService.updateUser(id, user);
        log.info("updated user with id {}", id);
        return updatedUserVO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(value="users", allEntries = true)
    public void deleteUser(@PathVariable("id") long id) {
        log.info("fetching & deleting User with id {}", id);
        userService.deleteUser(id);
        log.info("deleted user with id {}", id);
    }



    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value="users", key="'all_users'")
    public Page<UserVO> findAll(@ApiParam(value = "The page number (zero-based)")
                                @RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                @ApiParam(value = "Tha page size")
                                @RequestParam(value = "size", required = true, defaultValue = "10") Integer size) {
        log.info("getting all the users");
        Page<UserVO> users = userService.findAll(new PageRequest(page,size));
        users.forEach(user -> user.add(linkTo(UserRestController.class).slash(user.getUserId()).withSelfRel()));
        return users;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(value="users", allEntries = true)
    public void deleteAllUsers() {
        log.info("deleting all users");
        userService.deleteAllUsers();
        log.info("deleted all users");
    }

}
