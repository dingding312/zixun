package com.dq.service;

import com.dq.dao.NewsDAO;
import com.dq.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DQ on 2016/8/20.
 */
@Service
public class NewsService {
    @Autowired
    NewsDAO newsDAO;

     public List<News> getLatestNews(int userId, int offset, int limit){
         return newsDAO.selectByUserIdAndOffset(userId, offset, limit);
     }
}
