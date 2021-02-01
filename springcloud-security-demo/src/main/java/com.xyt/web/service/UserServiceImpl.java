package com.xyt.web.service;

import com.xyt.web.entities.Permission;
import com.xyt.web.entities.User;
import com.xyt.web.mapper.PermissionMapper;
import com.xyt.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: UserServiceImpl
 * @Description:
 * @Author: abby
 * @Date: 2021/1/28 9:59
 */
@Service
public class UserServiceImpl implements UserService {

    //连入数据库 引入UserMapper
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 实现用户认证
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //通过数据库查询User
        User user = userMapper.getUserByUserName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user != null){

            List<Permission> permissions = permissionMapper.selectByUserId(user.getId());
            //设置权限
            permissions.forEach(permission -> {
                if (permission != null && !StringUtils.isEmpty(permission.getEname())){
                    GrantedAuthority grantedAuthority =
                            new SimpleGrantedAuthority(permission.getEname());
                    authorities.add(grantedAuthority);
                }
            });
            //封装成UserDetails的实现类
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(), user.getPassword(), authorities
            );
        } else {
            throw new UsernameNotFoundException("用户名不存在");
        }

    }
}
