package com.mkalaimalai.service;

import com.mkalaimalai.domain.User;
import com.mkalaimalai.exception.ResourceNotFoundException;
import com.mkalaimalai.mapper.UserMapper;
import com.mkalaimalai.repository.UserRepository;
import com.mkalaimalai.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalaimam on 7/14/17.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserVO createUser(UserVO userVO) {
       User user =  userRepository.save(userMapper.createUser(userVO));
        return userMapper.createUserVO(user);
    }

    public UserVO findUserById(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            logger.error("Unable to find User with id {}", id);
            throw new ResourceNotFoundException(id, "user not found");
        }
        return userMapper.createUserVO(user);
    }

    public List<UserVO> findAll(){
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("users not found");
        }
        List<UserVO> userVOs = new ArrayList<UserVO>();
        users.forEach( user -> userVOs.add(userMapper.createUserVO(user)));
        return userVOs;
    }

    public User findById(Long id){
        User user = userRepository.findById(id);
        return user;
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public UserVO updateUser(Long id,  UserVO userVO){
        User user = findById(id);
        if (user == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            throw new ResourceNotFoundException(id, "user not found");
        }
        User updatedUser = userMapper.createUser(userVO);
        user.setId(id);
        User savedUser = userRepository.save(updatedUser);
        return userMapper.createUserVO(savedUser);
    }

    public void deleteUser(Long id){
        User user = findById(id);
        if (user == null) {
            logger.error("unable to delete. user with id {} not found.", id);
            throw new ResourceNotFoundException(id, "user not found");
        }
        userRepository.delete(id);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

}
