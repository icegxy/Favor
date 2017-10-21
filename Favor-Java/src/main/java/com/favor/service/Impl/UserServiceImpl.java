package com.favor.service.Impl;

import com.favor.dao.UserDao;
import com.favor.domain.User;
import com.favor.service.UserService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserServiceImpl implements UserService{

    @Resource(name= "userDao")
    private UserDao usrDao;

    /**
     * 根据用户姓名获取用户
     * @param name 用户名
     * @return 用户实体
     */
    public User getUserByName(String name) {
        return usrDao.getUserByName(name);
    }
}
