package com.xyt.web.mapper;

import com.xyt.web.entities.User;

/**
 * @className: UserMapper
 * @Description:
 * @Author: abby
 * @Date: 2021/1/28 10:01
 */
public interface UserMapper {

    public User getUserByUserName(String username);
}
