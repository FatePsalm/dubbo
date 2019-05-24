package com.test.wh.dao;

import com.test.wh.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User,Long> {

    @Query("from User where username = ?1")
    List<User> selectUserByName(String username);
}
