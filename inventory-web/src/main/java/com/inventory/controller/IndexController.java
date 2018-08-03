package com.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zkaigang on 2018/7/30.
 */
@Controller
public class IndexController {

    /**
     * 打开首页
     * @return
     */
    @RequestMapping(value="/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/top")
    public String top(){
        return "top";
    }

    @RequestMapping("/left")
    public String left(){
        return "left";
    }

    @RequestMapping("/right")
    public String right(){
        return "right";
    }
}
