package com.xyt.common;

import com.xyt.web.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: BaseEntity 基类
 * @Description:
 * @Author: abby
 * @Date: 2021/1/28 10:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer id;

    protected String remake; // 备注

    protected User createBy; //创建者

    protected Date createDate; // 创建时间

    protected User updateBy; //更新者

    protected Date updateDate; //更新时间
}
