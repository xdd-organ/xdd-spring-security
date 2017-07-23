package com.java.xdd.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/7/16.
 */
@Controller
@RequestMapping(value = "demo")
public class DemoController {
    @RequestMapping(value = "test1")
    @ResponseBody
    public String test1() {
        return "hello word1";
    }


    @RequestMapping(value = "test2")
    @ResponseBody
    public String test2() {
        return "hello word2";
    }

    @RequestMapping(value = "test3")
    @ResponseBody
    public String test3() {
        return "hello word3";
    }

    //失败访问
    @RequestMapping(value = "failure")
    @ResponseBody
    public String failure() {
        return "failure";
    }

    //失败访问
    @RequestMapping(value = "success")
    @ResponseBody
    public String success() {
        return "success";
    }

    //403
    @RequestMapping(value = "to403")
    @ResponseBody
    public String to403() {
        return "403";
    }

    //500
    @RequestMapping(value = "to500")
    @ResponseBody
    public String to500() {
        return "500";
    }

    //失败访问
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login() {
        return "login";
    }

    //登出
    @RequestMapping(value = "logout")
    @ResponseBody
    public String logout() {
        return "logout";
    }

    //去登录页面
    @RequestMapping(value = "toLogin")
    public String toLogin() {
        return "login";
    }


    @RequestMapping(value = "test")
    public ModelAndView test() {
        ModelAndView md = new ModelAndView("test2");
        md.addObject("abc", 9999);
        return md;
    }

}
