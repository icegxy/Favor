package com.favor.controller;

import com.favor.domain.User;
import com.favor.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @author icegxy
 * 用户相关控制器
 */
@Controller
public class UserController {

    private User user;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getUserByName(@PathVariable String name) {
        user = userService.getUserByName(name);
        System.out.println(user.getName());
        return "index";
    }

}
