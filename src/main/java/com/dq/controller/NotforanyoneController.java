package com.dq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DQ on 2016/8/22.
 */
@Controller
public class NotforanyoneController {
    @RequestMapping(value = {"/not"})
    @ResponseBody
    public String notforanyone(){
        return "You can";
    }
}
