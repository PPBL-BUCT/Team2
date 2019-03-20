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
        List<ClassAndGrade> buildings = classAndGradeService.getAll();
        map.put("code",0);
        map.put("msg","");
        map.put("count",buildings.size());

        map.put("data",buildings);
        System.out.println(map.get("data").toString());
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
}
