package cn.edu.buct.service.impl;

import cn.edu.buct.dao.StudentDao;
import cn.edu.buct.entity.Student;
import cn.edu.buct.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Qualifier(value = "studentDao")
    @Autowired
    private StudentDao studentDao;

    public Boolean add(Student student) {
        int i = studentDao.insert(student);
        return i>0;
    }

    public Boolean edit(Student student) {
        System.out.println(student);
        int num = studentDao.update(student);
        return num>0;
    }


    public Student get(Integer sid) {
        return studentDao.select(sid);
    }

    public List<Student> getAll() {
        return studentDao.selectAll();
    }

    @Override
    public List<Student> getAllByConditions(String sid, Integer sName, String nativePlace, Integer cid,String state) {
        return studentDao.selectAllByConditions(sid,sName,nativePlace,cid,state);
    }
}
