package cn.edu.buct.service.impl;

import cn.edu.buct.dao.LoginDao;
import cn.edu.buct.entity.*;
import cn.edu.buct.global.*;
import cn.edu.buct.service.LoginService;
import com.alibaba.fastjson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("loginService")
public class LoginServiceimpl implements LoginService {
    @Autowired
    @Qualifier(value = "loginDao")
    private LoginDao loginDao;

    public Verification Decode(String enData, String enKey) {
        Verification user = new Verification();
        try {
            String key = RSAUtils.decryptByPrivateKey(enKey);
            String plaionText = AESUtils.decryptData(key, enData);
            System.out.println(plaionText);
            //HashMap<String, Object> hashMap=JSON.parseObject(plaionText, HashMap.class);
            JSON json;
            json = JSON.parseObject(plaionText);
            Map map = (Map) json;
            String userName = (String) map.get("userName");
            String passWord = (String) map.get("passWord");
            String identifyingCode = (String) map.get("identifyingCode");
            user = new Verification(userName, passWord, identifyingCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean edit(User user) {
        int i = loginDao.update(user);
        return i>0;
    }

    @Override
    public User get(String uid) {
        return loginDao.select(uid);
    }

    @Override
    public User getbyUserName(String un) {
        return loginDao.selectByUserName(un);
    }

    @Override
    public boolean editTimes() {
        int i = loginDao.updateTimes();
        return i>0;
    }
}
