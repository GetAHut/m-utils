package com.xyt.web.mapper;

import com.xyt.web.entities.Permission;

import java.util.List;

/**
 * @className: PermissionMapper
 * @Description:
 * @Author: abby
 * @Date: 2021/1/28 10:20
 */
public interface PermissionMapper {

    List<Permission> selectByUserId(int userId);
}
