package com.test.wh.service;

import com.test.wh.po.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {


    User save(User user);

    List<User> selectUserByName(String user);

}
