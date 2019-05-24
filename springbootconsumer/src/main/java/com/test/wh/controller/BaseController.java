package com.test.wh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.test.wh.po.User;
import com.test.wh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaseController {

    @Autowired
    private UserService userService;


    @RequestMapping("getUser")
    @ResponseBody
    public String getUser(){
        System.out.println("aaaaa");
        User user = new User();
        user.setUsername("王五");
        user.setEmail("534869534@QQ.com");

        List<User> list = userService.getUser("");

        return list.toString();
    }



}
