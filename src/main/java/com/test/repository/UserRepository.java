package com.test.repository;


import com.test.model.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by egguncle on 17-1-14.
 * <p>
 * 用户数据库相关接口
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * 查询出的list需要进行相应转置来更加符合正常的阅读习惯（新的在前面，旧的在后面）
     *
     * @return
     */
    @Override
    @Query(value = "select * from table_user ORDER BY userId DESC ",nativeQuery = true)
    List<UserEntity> findAll();

    /**
     * 使用id获取对应用户数据
     *
     * @param id
     * @return
     */
    @Query(value = "select * from table_user where userId=?1", nativeQuery = true)
    UserEntity findUser(int id);

    /**
     * 输入用户名密码进行登录
     *
     * @param userName
     * @param passwd
     * @return
     */
    @Query(value = "select * from table_user where username=?1 and userpasswd=?2", nativeQuery = true)
    UserEntity login(String userName, String passwd);

    /**
     * 使用用户名和token进行不完全登录，来执行一些限制范围内的请求
     *
     * @param userName
     * @param token
     * @return
     */
    @Query(value = "select * from table_user where username=?1 and token=?2", nativeQuery = true)
    UserEntity loginWithToken(String userName, String token);


    /**
     * 输入用户名获取用户对象，用来在注册的时候判断用户名是否重复
     * 这里使用list而不是单个对象返回是因为避免在测试时注册过两个一样的用户名，从而返回集合而不是单个对象
     *
     * @param userName
     * @return
     */
    @Query(value = "select * from table_user where  username=?1", nativeQuery = true)
    List<UserEntity> getUserByName(String userName);

}
