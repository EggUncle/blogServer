package com.test.repository;


import com.test.model.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by egguncle on 17-1-14.
 *
 * 用户数据库相关接口
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * 使用id获取对应用户数据
     *
     * @param id
     * @return
     */
    @Query(value = "select * from table_user where userId=?1", nativeQuery = true)
    UserEntity findUser(int id);

    /**
     *
     * 输入用户名密码进行登录
     * @param userName
     * @param passwd
     * @return
     */
    @Query(value = "select * from table_user where username=?1 and userpasswd=?2", nativeQuery = true)
    UserEntity login(String userName,String passwd);



}
