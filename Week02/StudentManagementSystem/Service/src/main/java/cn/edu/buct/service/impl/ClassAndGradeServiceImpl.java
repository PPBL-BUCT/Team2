package cn.edu.buct.service.impl;

import cn.edu.buct.entity.ClassAndGrade;
import cn.edu.buct.dao.ClassAndGradeDao;
import cn.edu.buct.service.ClassAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classAndGradeService")
public class ClassAndGradeServiceImpl implements ClassAndGradeService {
    @Qualifier(value = "classAndGradeDao")
    @Autowired
    private ClassAndGradeDao classAndGradeDao;

    public Boolean add(ClassAndGrade classAndGrade) {
        classAndGrade.setAmount(0);
        int num = classAndGradeDao.insert(classAndGrade);
        return num>0;
    }

    public Boolean edit(ClassAndGrade classAndGrade) {
        System.out.println(classAndGrade);
        int num = classAndGradeDao.update(classAndGrade);
        return num>0;
    }

    public ClassAndGrade get(Integer id) {
        return classAndGradeDao.select(id);
    }

    public List<ClassAndGrade> getAll() {
        return classAndGradeDao.selectAll();
    }

    public List<ClassAndGrade> getAllByConditions(String year, String cName) {
        System.out.println("service层接受到"+classAndGradeDao.selectAllByConditions(year,cName));
        return classAndGradeDao.selectAllByConditions(year,cName);
    }
}
