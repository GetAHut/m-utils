package com.xyt.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: IndexController
 * @Description:
 * @Author: abby
 * @Date: 2021/1/21 15:57
 */
@RestController
@RequestMapping("/r")
public class IndexController {

    @RequestMapping(value = "demo", method = RequestMethod.GET)
    public String demo(){
        return "spring security demo";
    }

}
