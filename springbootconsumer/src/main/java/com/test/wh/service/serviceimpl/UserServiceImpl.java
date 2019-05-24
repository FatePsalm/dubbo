package com.test.wh.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.test.wh.dao.UserDAO;
import com.test.wh.po.User;
import com.test.wh.service.TestService;
import com.test.wh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Reference(version ="1.0.0")
    private TestService testService;

    @Override
    public List<User> getUser(String username) {
        return testService.getUser(username);
    }


}
