package cn.edu.buct.service;

import cn.edu.buct.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
    Boolean add(Student student);
    Boolean edit(Student student);
    Student get(Integer bsn);
    List<Student> getAll();
    List<Student> getAllByConditions(String sid, String sName);
}
