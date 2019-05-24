package com.test.wh.service.serviceimpl;


import com.test.wh.dao.UserDAO;
import com.test.wh.po.User;
import com.test.wh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public User save(User user) {
        System.out.println(11111);
        return new User();
    }

    @Override
    public List<User> selectUserByName(String UserName) {

        List<User> list = userDAO.findAll();
        return list ;
    }
}
