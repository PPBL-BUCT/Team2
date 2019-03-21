package cn.edu.buct.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Student {
    private Integer id;//主键序号

    private String sid;

    private String sName;

    private String gender;

    private Date birthday;

    private Integer age;

    private String nativePlace;

    private String state;

    private Integer cid;

    private String cName;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sid='" + sid + '\'' +
                ", sName='" + sName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", nativePlace='" + nativePlace + '\'' +
                ", state='" + state + '\'' +
                ", cid=" + cid +
                ", cName='" + cName + '\'' +
                '}';
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}