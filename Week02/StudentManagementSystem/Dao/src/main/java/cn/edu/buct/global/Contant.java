package cn.edu.buct.global;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典类，提供常量
 */
public class Contant {
    //角色
    public static final String ROLE_USER="用户";
    public static final String ROLE_FIXER="维修人员";
    public static final String ROLE_ASSIGNMENT_ADMINISTRATOR="任务管理员";
    public static final String ROLE_BACKSTAGE_ADMINISTRATOR="后台管理员";
    public static List<String> getRole(){
        List<String> list = new ArrayList<String>();
        list.add(ROLE_USER);
        list.add(ROLE_FIXER);
        list.add(ROLE_ASSIGNMENT_ADMINISTRATOR);
        list.add(ROLE_BACKSTAGE_ADMINISTRATOR);
        return list;
    }

    //性别
    public static final String MALE="男";
    public static final String FEMALE="女";
    public static List<String> getGender(){
        List<String> list = new ArrayList<String>();
        list.add(MALE);
        list.add(FEMALE);
        return list;
    }

    //处理状态
    public static final String STATEMENT_UPLODED="已提交";
    public static final String STATEMENT_DISTRIBUTED="已被分派";
    public static final String STATEMENT_REFUSE="已打回";
    public static final String STATEMENT_ACCEPTED="已被接收";
    public static final String STATEMENT_FIXED="修理完成";
    public static final String STATEMENT_CANCEL="已取消";

    public static List<String> getStatement(){
        List<String> list = new ArrayList<String>();
        list.add(STATEMENT_UPLODED);
        list.add(STATEMENT_DISTRIBUTED);
        list.add(STATEMENT_REFUSE);
        list.add(STATEMENT_ACCEPTED);
        list.add(STATEMENT_FIXED);
        list.add(STATEMENT_CANCEL);
        return list;
    }

}
