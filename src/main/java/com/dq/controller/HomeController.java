package com.dq.controller;

import com.dq.model.HostHolder;
import com.dq.model.News;
import com.dq.model.ViewObject;
import com.dq.service.NewsService;
import com.dq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by DQ on 2016/8/22.
 */
@Controller
public class HomeController {
    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @Autowired
    HostHolder hostHolder;

    private List<ViewObject> getNews(int userId, int offset, int limit){
        List<News> newsList = newsService.getLatestNews(userId, offset, limit);
        List<ViewObject> vos = new ArrayList<ViewObject>();

        //遍历
        for (News news : newsList){
            ViewObject vo = new ViewObject();
            vo.set("news", news);
            vo.set("user", userService.getUser(news.getUserId()));
            vos.add(vo);
        }
        return vos;
    }

    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model){
        model.addAttribute("vos", getNews(0, 0, 10));
        return "home";
    }

    @RequestMapping(value = {"/user/{userId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String userIndex(Model model,
                            @PathVariable("userId") int userId){
        model.addAttribute("vos", getNews(userId, 0, 10));
        return "home";
    }
}
