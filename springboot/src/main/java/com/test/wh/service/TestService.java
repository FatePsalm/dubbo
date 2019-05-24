package com.test.wh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.test.wh.po.User;
import org.springframework.stereotype.Component;

import java.util.List;

public interface TestService {

    public List<User> getUser(String username);

}
