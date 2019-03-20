package cn.edu.buct.service;

import cn.edu.buct.entity.ClassAndGrade;

import java.util.List;

public interface ClassAndGradeService {
    Boolean add(ClassAndGrade classAndGrade);
    Boolean edit(ClassAndGrade classAndGrade);
//    ClassAndGrade get(String sn);
//    Boolean getByOpenId(String openid);
//    ClassAndGrade getRoleByOpenId(String openid);
    List<ClassAndGrade> getAll();
//    List<ClassAndGrade> getEmployee();
//    List<ClassAndGrade> getUser();
//    List<ClassAndGrade> getByRole(String role);
}
