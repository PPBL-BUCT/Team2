package cn.edu.buct.controller;

import cn.edu.buct.entity.User;
import cn.edu.buct.entity.Verification;
import cn.edu.buct.global.Content;
import cn.edu.buct.service.LoginService;
import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller("globalController")
public class GlobalController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "loginUI";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:to_login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> submit(HttpSession session, HttpServletResponse response, @RequestParam String requestData, @RequestParam String encryptKey) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String generateCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println(requestData);
        System.out.println(encryptKey);
        Verification decodeVerification = new Verification();
        decodeVerification = loginService.Decode(requestData, encryptKey);
        String userName = decodeVerification.getUserName();
        String passWord = decodeVerification.getPassWord();
        String secureCode = decodeVerification.getIdentifyingCode();
        System.out.println(userName + passWord + secureCode);
        User user = loginService.getbyUserName(userName);//搜索数据库返回密码
        System.out.println("user:" + user);
        String pwFromDatabase = null;
        if (!secureCode.equals(generateCode)) {
            System.out.println("验证码错误，请重试");
            String s = "验证码错误，请重试";
            map.put("msg", s);
            return map;
        }
        if (user == null) {
            Integer countRealateUser = (Integer) session.getAttribute("countRealateUser");
            if (countRealateUser == null) {
                session.setAttribute("countRealateUser", Content.MAX_LOGIN_AMOUNT);//为不存在的用户创建临时登陆次数
                System.out.println("用户名或密码错误，您还有" + Content.MAX_LOGIN_AMOUNT + "次尝试机会");
                String s = "账户或密码错误，本日还剩" + Content.MAX_LOGIN_AMOUNT + "次尝试机会";
                map.put("msg", s);
                return map;
            } else if (countRealateUser == 0) {
                System.out.println("账户锁定，请明日再试");
                String s = "账户锁定，请明日再试";
                map.put("msg", s);
                return map;
            } else {
                countRealateUser--;
                session.setAttribute("countRealateUser", countRealateUser);
                System.out.println("用户名或密码错误，您还有" + countRealateUser + "次尝试机会");
                String s = "用户名或密码错误，您还有" + countRealateUser + "次尝试机会";
                map.put("msg", s);
                return map;
            }
        } else {
            pwFromDatabase = user.getPw();
            if (pwFromDatabase.equals(passWord)) {
                session.setAttribute("user", user);
                System.out.println("成功");
                String s = "成功";
                map.put("msg", s);
                return map;
            } else {
                Integer countRealateUser = user.getTm();
                Integer countRealateSession = (Integer) session.getAttribute("countRealateUser");
                if (countRealateSession != null) {
                    if (countRealateUser != 0) {
                        if (countRealateUser <= countRealateSession) {
                            countRealateUser--;
                        } else {
                            countRealateUser = --countRealateSession;
                            session.setAttribute("countRealateUser", countRealateUser);
                        }
                    }
                    user.setTm(countRealateUser);
                    loginService.edit(user);
                    String s = "账户或密码错误，本日还剩" + countRealateUser + "次尝试机会";
                    map.put("msg", s);
                    return map;
                } else {
                    String s = "账户锁定，请明日再试";
                    map.put("msg", s);
                    return map;
                }
            }
        }
    }
}
