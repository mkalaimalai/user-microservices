package com.mkalaimalai.repository;

import com.mkalaimalai.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kalaimam on 7/14/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserName(String userName);
    public User findById(Long id);
    public List<User> findAll();
}
