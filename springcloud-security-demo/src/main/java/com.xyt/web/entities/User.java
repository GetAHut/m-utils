package com.xyt.web.entities;

import com.xyt.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className: User  《entities 转移至boot包中》 此处测试用
 * @Description:
 * @Author: abby
 * @Date: 2021/1/28 10:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity {

    private String userName; //用户名

    private String password; // 密码

}
