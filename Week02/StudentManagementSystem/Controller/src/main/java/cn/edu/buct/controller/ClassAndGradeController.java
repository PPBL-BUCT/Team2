package cn.edu.buct.controller;


import cn.edu.buct.entity.ClassAndGrade;
import cn.edu.buct.entity.Student;
import cn.edu.buct.global.Contant;
import cn.edu.buct.service.ClassAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("classAndGradeController")
@RequestMapping("/class")
public class ClassAndGradeController {
    @Autowired
    private ClassAndGradeService classAndGradeService;

    @RequestMapping("/classes")
    @ResponseBody
    public Map<String,Object> classes()
    {
        Map map = new HashMap();
        List<ClassAndGrade> classes = classAndGradeService.getAll();
        map.put("code",0);
        map.put("msg","");
        map.put("count",classes.size());

        map.put("data",classes);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/classlist")
    @ResponseBody
    public Map<String,Object> classList(@RequestParam int page, @RequestParam int limit)
    {
        Map<String, Object> map = new HashMap<>();
        List<ClassAndGrade> classAndGrades = classAndGradeService.getAll();
        List classAndGrades2;
        if(page * limit -1<classAndGrades.size()-1)
        {
            classAndGrades2 = classAndGrades.subList((page - 1)*limit,page * limit -1);
        }
        else {
            classAndGrades2 = classAndGrades.subList((page - 1)*limit,classAndGrades.size());
        }
        map.put("code",0);
        map.put("msg","");
        map.put("count",classAndGrades.size());
        map.put("data",classAndGrades2);
        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(ClassAndGrade classAndGrade){
        boolean flag = classAndGradeService.add(classAndGrade);
        return flag;
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(ClassAndGrade classAndGrade){
        boolean flag = classAndGradeService.edit(classAndGrade);
        return flag;
    }

    @RequestMapping("/class_list")
    public String roleList(){
        return "class/class_list";
    }

    @RequestMapping("/classSearch")
    @ResponseBody
    public Map<String,Object> search(@RequestParam int page, @RequestParam int limit,String year, String cName){
        Map<String,Object> map = new HashMap<>();
        List<ClassAndGrade> classAndGrades;
        List classAndGrades2;
        //如果内容为空改为Null
        if(year=="") {
            year=null;
        }
        if (cName=="") {
            cName=null;
        }
        System.out.println(cName+"controll层");
        //没有搜索条件则返回所有数据
        if(year==null&&cName==null){
            classAndGrades = classAndGradeService.getAll();
            System.out.println("所有数据。controller");

        }
        else {
            // System.out.println(studentService.getAllByConditions(sid,sName));
            classAndGrades = classAndGradeService.getAllByConditions(year,cName);
            System.out.println("controller层接收到"+classAndGrades);
        }
        //分页操作，直接复制
        if(page * limit -1<classAndGrades.size()-1) {
            classAndGrades2 = classAndGrades.subList((page - 1)*limit,page * limit -1);
        }
        else {
            classAndGrades2 = classAndGrades.subList((page - 1)*limit,classAndGrades.size());
        }
        //渲染表，直接复制
        map.put("code",0);
        map.put("msg","");
        map.put("count",classAndGrades.size());
        map.put("data",classAndGrades2);
        System.out.println("classAndGrade2"+classAndGrades2);
        //返回list渲染
        return map;
    }
}
