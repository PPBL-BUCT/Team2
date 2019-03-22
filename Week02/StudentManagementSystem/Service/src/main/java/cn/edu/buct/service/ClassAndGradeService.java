package cn.edu.buct.service;

import cn.edu.buct.entity.ClassAndGrade;

import java.util.List;

public interface ClassAndGradeService {
    Boolean add(ClassAndGrade classAndGrade);

    Boolean edit(ClassAndGrade classAndGrade);

    ClassAndGrade get(Integer id);

    List<ClassAndGrade> getAll();

    List<ClassAndGrade> getAllByConditions(String year, String cName);
}
