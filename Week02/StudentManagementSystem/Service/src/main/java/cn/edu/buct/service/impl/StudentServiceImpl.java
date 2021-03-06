package cn.edu.buct.service.impl;

import cn.edu.buct.dao.ClassAndGradeDao;
import cn.edu.buct.dao.StudentDao;
import cn.edu.buct.entity.ClassAndGrade;
import cn.edu.buct.entity.Student;
import cn.edu.buct.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Qualifier(value = "studentDao")
    @Autowired
    private StudentDao studentDao;
    @Qualifier(value = "classAndGradeDao")
    @Autowired
    private ClassAndGradeDao classAndGradeDao;

    public Boolean add(Student student) {
        Date birthday = student.getBirthday();
        //获取当前系统时间
        Calendar cal = Calendar.getInstance();
        //如果出生日期大于当前时间，则抛出异常
        if (cal.before(birthday)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        //取出系统当前时间的年、月、日部分
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        //将日期设置为出生日期
        cal.setTime(birthday);
        //取出出生日期的年、月、日部分
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //当前年份与出生年份相减，初步计算年龄
        int age = yearNow - yearBirth;
        //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
        if (monthNow <= monthBirth) {
            //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        student.setAge(age);
        int i = studentDao.insert(student);
        int j=0;
        if(i>0){
            ClassAndGrade classAndGrade = classAndGradeDao.select(student.getCid());
            int amount = classAndGrade.getAmount();
            amount++;
            classAndGrade.setAmount(amount);
            System.out.println(classAndGrade);
            j=classAndGradeDao.update(classAndGrade);
        }
        return j>0;
    }

    public Boolean edit(Student student) {
        Date birthday = student.getBirthday();
        //获取当前系统时间
        Calendar cal = Calendar.getInstance();
        //如果出生日期大于当前时间，则抛出异常
        if (cal.before(birthday)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        //取出系统当前时间的年、月、日部分
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        //将日期设置为出生日期
        cal.setTime(birthday);
        //取出出生日期的年、月、日部分
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //当前年份与出生年份相减，初步计算年龄
        int age = yearNow - yearBirth;
        //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
        if (monthNow <= monthBirth) {
            //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        student.setAge(age);
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

    public List<Student> getAllByConditions(String sid, String sName) {
        System.out.println("service层接受到"+studentDao.selectAllByConditions(sid,sName));
        return studentDao.selectAllByConditions(sid,sName);
    }
}
