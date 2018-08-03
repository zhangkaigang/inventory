package com.inventory.controller;

import com.inventory.entity.User;
import com.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zkaigang on 2018/7/26.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value="/user")
    public ModelAndView queryUsers(User user){
        ModelAndView mav = new ModelAndView();
        List<User> userList = userService.selectListNoPage(user);
        mav.addObject("userList",userList);
        mav.setViewName("user/user_list");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/user/{id}")
    public User findUsersById(@PathVariable("id")Integer id){
        return userService.findUsersById(id);
    }


}
