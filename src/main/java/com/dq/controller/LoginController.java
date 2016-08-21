package com.dq.controller;

import com.dq.service.UserService;
import com.dq.util.ZixunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by DQ on 2016/8/20.
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(path = {"/reg"},method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String reg(@RequestParam("username") String username,
                      @RequestParam("password") String password){
        try{
            Map<String, Object> map = userService.register(username, password);
            if(map.isEmpty()){
                return ZixunUtil.getJsonString(0, "注册成功");
            }
            else
                return ZixunUtil.getJsonString(1,map);
        }catch(Exception e){
            return ZixunUtil.getJsonString(1, "注册异常");
        }
    }
}
