package com.favor.service;

import com.favor.domain.User;

/**
 * @author icegxy
 * 用户业务逻辑处理接口
 */
public interface UserService {

    /**
     * 根据用户姓名获取用户
     * @param name 用户名
     * @return 用户实体
     */
    User getUserByName(String name);
}
