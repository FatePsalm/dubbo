package com.test.wh.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;

import com.test.wh.dao.UserDAO;
import com.test.wh.po.User;
import com.test.wh.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Service(version = "1.0.0",interfaceClass = TestService.class)
public class TestServiceImpl implements TestService {

    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> getUser(String username) {
        System.out.println("调用到了");
        List<User> list = userDAO.findAll();
        return list ;
    }
}
