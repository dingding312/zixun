package com.dq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DQ on 2016/8/16.
 */

@Controller
public class IndexController {
    @RequestMapping(value = {"/"})
    @ResponseBody
    public String index(){
        return "hello zixun";
    }
}
