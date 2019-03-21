package cn.edu.buct.controller;

import cn.edu.buct.entity.ClassAndGrade;
import cn.edu.buct.entity.Student;
import cn.edu.buct.service.ClassAndGradeService;
import cn.edu.buct.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        System.out.println(student);
        boolean flag = studentService.add(student);
        return flag;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(Student student){
        boolean flag = studentService.edit(student);
        return flag;

    }

    @RequestMapping("/student_list")
    public String roleList(){
        return "student/student_list";
    }
}
