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
//
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
//
//    public void remove(String sn) {
//        classAndGradeDao.delete(sn);
//    }
//
    public ClassAndGrade get(Integer id) {
        return classAndGradeDao.select(id);
    }
//
//    public ClassAndGrade getRoleByOpenId(String openid){
//        List<ClassAndGrade> list = classAndGradeDao.selectByOpenId(openid);
//        return list.get(0);
//    }
//
//    public Boolean getByOpenId(String openid) {
//        if(openid!=null)
//        {
//            List<ClassAndGrade> list = classAndGradeDao.selectByOpenId(openid);
//            if (!list.isEmpty())
//            {
//                return true;
//            }
//            else{
//                return false;
//            }
//        }
//        else {
//            return false;
//        }
//    }
//
    public List<ClassAndGrade> getAll() {
        return classAndGradeDao.selectAll();
    }
//
//    @Override
//    public List<ClassAndGrade> getEmployee() {
//        return classAndGradeDao.selectEmployee();
//    }
//
//    public List<ClassAndGrade> getUser() {
//        return classAndGradeDao.selectUser();
//    }
//
//    public List<ClassAndGrade> getByRole(String role) {
//        return classAndGradeDao.selectByRole(role);
//    }
}
