package cn.edu.buct.controller;

import cn.edu.buct.entity.User;
import cn.edu.buct.entity.Verification;
import cn.edu.buct.global.Content;
import cn.edu.buct.service.LoginService;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("globalController")
public class GlobalController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "loginUI";
    }

    @RequestMapping("/login")
    @ResponseBody
    //@RequestParam String userName, @RequestParam String passWord, @RequestParam String secureCode
    public String submit(HttpSession session, HttpServletResponse response, @RequestParam String requestData, @RequestParam String encryptKey) throws IOException {
        String generateCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println(generateCode);
        Verification decodeVerification = new Verification();
        decodeVerification = loginService.Decode(requestData,encryptKey);
        String userName = decodeVerification.getUserName();
        String passWord = decodeVerification.getPassWord();
        String secureCode = decodeVerification.getIdentifyingCode();
        System.out.println(userName+passWord+secureCode);
        User user = loginService.getbyUserName(userName);//搜索数据库返回密码
        String pwFromDatabase = null;
        if (secureCode != generateCode) {
            return "验证码错误，请重试";
        }
        if (user == null) {
            Integer countRealateUser = (Integer) session.getAttribute("countRealateUser");
            if (countRealateUser == null) {
                session.setAttribute("countRealateUser", Content.MAX_LOGIN_AMOUNT);//为不存在的用户创建临时登陆次数
                return "";
            } else if (countRealateUser == 0) {
                return "";
            } else {
                countRealateUser--;
                session.setAttribute("countRealateUser", countRealateUser);
                return "";
            }
        } else {
            pwFromDatabase = user.getPw();
            if (pwFromDatabase.equals(passWord)) {
                session.setAttribute("user", user);
                response.sendRedirect("http://localhost:8080/index");
                return "成功";
            } else {
                Integer countRealateUser = user.getTm();
                if (countRealateUser != 0) {
                    countRealateUser--;
                    user.setTm(countRealateUser);
                    loginService.edit(user);
                    return ("账户或密码错误，本日还剩" + countRealateUser + "次");
                } else {
                    return "账户锁定，请明日再试";
                }
            }
        }
    }
}
