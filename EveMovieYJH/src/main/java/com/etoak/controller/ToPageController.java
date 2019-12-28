package com.etoak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 杨炯昊 on 2019/11/1.
 */

@Controller
@RequestMapping("/to_page")
public class ToPageController {

    @RequestMapping("to_add")
    public String toAddPage(){
        return "add";
    }

    @RequestMapping("to_xq")
    public String toXqPage(){
        return "xq";
    }
}
