package cn.edu.buct.dao;

import cn.edu.buct.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("loginDao")
public interface LoginDao {
    Integer insert(User user);
    Integer update(User user);
    User select(String uid);
    User selectbyUserName(String un);
    List<User> selectAll();
    List<User> selectAllByConditions(@Param("un") String un, @Param("pw") String pw, @Param("tm") String tm);
}
