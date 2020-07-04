package com.liuyang.community.controller;

import com.liuyang.community.mapper.UserMapper;
import com.liuyang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        System.out.println("进入了 index");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null) {
                    request.getSession().setAttribute("user",user);
                    System.out.println("找到了user 并进行了注册");
                }
                break;
            }
        }
        return "index";
    }
}
