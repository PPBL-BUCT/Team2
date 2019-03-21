package cn.edu.buct.controller;

import cn.edu.buct.entity.ClassAndGrade;
import cn.edu.buct.entity.Student;
import cn.edu.buct.service.ClassAndGradeService;
import cn.edu.buct.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("studentController")
@RequestMapping("/student")
public class StudentController {
    @Qualifier(value = "studentService")
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassAndGradeService classAndGradeService;

    @RequestMapping("/studentlist")
    @ResponseBody
    public Map<String, Object> slist(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> map = new HashMap<>();
        List<Student> students = studentService.getAll();
        List students2;
        if(page * limit -1<students.size()-1)
        {
            students2 = students.subList((page - 1)*limit,page * limit -1);
        }
        else {
            students2 = students.subList((page - 1)*limit,students.size());
        }
        map.put("code",0);
        map.put("msg","");
        map.put("count",students.size());
        map.put("data",students2);
        System.out.println(students);
        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(Student student){
        boolean flag = studentService.add(student);
        return flag;
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(Student department){
        boolean flag = studentService.edit(department);
        return flag;

    }

    @RequestMapping("/student_list")
    public String roleList(){
        return "student/student_list";
    }

    @RequestMapping("/studentSearch")
    public Map<String,Object> search(@RequestParam int page, @RequestParam int limit,String sid, String sName, String nativePlace, Integer cid,String state){
        Map<String,Object> map = new HashMap<>();
        List<Student> students = studentService.getAll();
        List students2;
        //如果内容为空改为Null
        if(sid=="") {
            sid=null;
        }
        if (sName=="") {
            sName=null;
        }
        if (nativePlace==""){
            nativePlace=null;
        }
        if(state==""){
            state=null;
        }
        //没有搜索条件则返回所有数据
        if(sid==null||sName==null||nativePlace==null||cid==null||state==null){
            studentService.getAll();
        }
        else {
            studentService.getAllByConditions(sid,sName,nativePlace,cid,state);
        }
        //分页操作，直接复制
        if(page * limit -1<students.size()-1) {
            students2 = students.subList((page - 1)*limit,page * limit -1);
        }
        else {
            students2 = students.subList((page - 1)*limit,students.size());
        }
        //渲染表，直接复制
        map.put("code",0);
        map.put("msg","");
        map.put("count",students.size());
        map.put("data",students2);
        //返回list渲染
       return map;
    }
}
