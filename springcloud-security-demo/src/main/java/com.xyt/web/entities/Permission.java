package com.xyt.web.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className: Permission
 * @Description:
 * @Author: abby
 * @Date: 2021/1/28 10:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Permission {

    private String permissionName; //权限名称

    private String ename; // 权限英文名
}
