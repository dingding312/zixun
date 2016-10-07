package com.dq.interceptor;

import com.dq.dao.LoginTicketDAO;
import com.dq.dao.UserDAO;
import com.dq.model.HostHolder;
import com.dq.model.LoginTicket;
import com.dq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by DQ on 2016/8/21.
 */
@Component
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //这个方法主要是找到登陆的这个用户到底是谁
        String ticket = null;
        //判断是否含有"ticket"这个名字的cookie，如果有，把名字为"ticket"的cookie值赋值给ticket
        if(httpServletRequest.getCookies() != null){
            for(Cookie cookie : httpServletRequest.getCookies()){
                if(cookie.getName().equals("ticket")){
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        //存在名字为"ticket"并不一定就说明，用户已经登录过了，有可能是伪造的，也有可能已经过期了，还有可能是无效的，为了保险起见，再做一次判断
        if(ticket != null) {
            LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0) {
                //说明ticket是无效的，表明没有登陆过或者登陆期限已过期,这个时候就不用做其他东西了，继续往下执行，返回真
                return true;
            }

            //如果ticket是有效的，说明用户已经登录过，或者登陆期限还在有效期内
            //这个时候在知道“你说谁”的情况下，为了能够在进入Controller之后还能记住“你是谁”，必须把“你”记下来，因为目前还处在请求进入Controller之前的部分
            //需要保存用户，让别人也可以调用

            User user = userDAO.selectById(loginTicket.getId());
            //以一种依赖注入的方式来保存，也许后面很多业务层或者控制层需要用到这个用户，要随时知道当前调用该线程的人是谁
            hostHolder.setUser(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null && hostHolder.getUser() != null){
            modelAndView.addObject("user", hostHolder.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        hostHolder.clear();
    }
}
