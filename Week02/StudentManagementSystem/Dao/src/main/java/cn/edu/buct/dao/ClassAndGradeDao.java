package cn.edu.buct.dao;

import cn.edu.buct.entity.ClassAndGrade;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("classAndGradeDao")
public interface ClassAndGradeDao {
    Integer insert(ClassAndGrade classAndGrade);
    Integer update(ClassAndGrade classAndGrade);
    ClassAndGrade select(Integer id);
    List<ClassAndGrade> selectAll();


}
