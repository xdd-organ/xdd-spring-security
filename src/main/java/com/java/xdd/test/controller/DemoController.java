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
        return "hello word";
    }

    @RequestMapping(value = "test2")
    public ModelAndView test2() {
        ModelAndView md = new ModelAndView("test2");
        md.addObject("abc", 9999);
        return md;
    }

}
