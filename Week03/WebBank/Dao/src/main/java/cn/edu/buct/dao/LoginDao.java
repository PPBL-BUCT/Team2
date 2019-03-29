package cn.edu.buct.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("LoginDao")
public interface LoginDao {
    Integer insert(LoginDao signInDao);
    Integer update(LoginDao signInDao);
    LoginDao select(Integer uid);
    List<LoginDao> selectAll();
    List<LoginDao> selectAllByConditions(@Param("un") String un, @Param("pw") String pw);


}
