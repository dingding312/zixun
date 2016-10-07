package com.dq.controller;

import com.dq.service.UserService;
import com.dq.util.ZixunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    public String reg(Model model,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "rember", defaultValue = "0") int rememberme,
                      HttpServletResponse response){
        try{
            Map<String, Object> map = userService.register(username, password);
            if(map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                if(rememberme > 0){
                    cookie.setMaxAge(3600*24*5);
                }
                //设置path全栈有效
                cookie.setPath("/");
                //添加cookie到response中
                response.addCookie(cookie);
                return ZixunUtil.getJsonString(0, "注册成功");
            }
            else
                return ZixunUtil.getJsonString(1,map);
        }catch(Exception e){
            return ZixunUtil.getJsonString(1, "注册异常");
        }
    }

    @RequestMapping(path = {"/login/"},method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(Model model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "rember", defaultValue = "0") int rememberme) {
        try {
            Map<String, Object> map = userService.login(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                if (rememberme > 0) {
                    cookie.setMaxAge(3600 * 24 * 5);
                }
                //设置path全栈有效
                cookie.setPath("/");
                return ZixunUtil.getJsonString(0, "登陆成功");
            } else
                return ZixunUtil.getJsonString(1, map);
        } catch (Exception e) {
            return ZixunUtil.getJsonString(1, "登陆异常");
        }
    }

    @RequestMapping(value = {"/logout"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/";
    }
}
