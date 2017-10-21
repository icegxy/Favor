package com.favor.dao;

import com.favor.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author icegxy
 * 用户相关数据库操作接口
 */
@Repository
public interface UserDao {

    /**
     * 根据用户姓名获取用户
     * @param name 用户名
     * @return 用户实体
     */
    User getUserByName(String name);
}
