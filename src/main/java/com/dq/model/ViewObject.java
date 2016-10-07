package com.dq.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DQ on 2016/8/22.
 * ViewObject可以作为前端后端传递的容器，方便传递任何数据到Velocity
 */
public class ViewObject {
    private Map<String, Object> objs = new HashMap<String, Object>();

    public void set(String key, Object value){
        objs.put(key, value);
    }

    public Object get(String key){
        return objs.get(key);
    }
}
