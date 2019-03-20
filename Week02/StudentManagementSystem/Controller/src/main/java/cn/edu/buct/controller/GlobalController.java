package cn.edu.buct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("globalController")
public class GlobalController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
