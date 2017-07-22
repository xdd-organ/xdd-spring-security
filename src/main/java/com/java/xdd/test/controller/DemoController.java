package com.java.xdd.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    //登录页面
    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }


    @RequestMapping(value = "test")
    public ModelAndView test() {
        ModelAndView md = new ModelAndView("test2");
        md.addObject("abc", 9999);
        return md;
    }

}
