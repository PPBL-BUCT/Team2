package cn.edu.buct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller("globalController")
public class GlobalController {
    @RequestMapping("/to_login")
    public String toLogin(){
        return "loginUI";
    }
}
