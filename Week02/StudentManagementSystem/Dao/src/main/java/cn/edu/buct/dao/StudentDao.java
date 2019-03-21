package cn.edu.buct.dao;

import cn.edu.buct.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentDao")
public interface StudentDao {
    Integer insert(Student student);
    Integer update(Student student);
    Student select(Integer id);
    List<Student> selectAll();
    List<Student> selectAllByConditions(@Param("sid") String sid, @Param("sName") String sName, @Param("nativePlace") String nativePlace, @Param("cid") Integer cid,@Param("state") String state);
}
