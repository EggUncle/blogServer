package com.test.repository;

import com.test.model.BlogEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by egguncle on 17-1-11.
 * <p>
 * 博客数据库相关接口
 * <p>
 *
 */


@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {


    /**
     * 查询出的list需要进行相应转置来更加符合正常的阅读习惯（新的在前面，旧的在后面）
     * @return
     */
    @Override
    @Query(value = "select * from table_blog ORDER BY blogId DESC", nativeQuery = true)
    List<BlogEntity> findAll();


    /**
     * 获取二十条最新的博客数据
     * @return
     */
    @Query(value = "select * from table_blog ORDER BY blogId DESC limit 20", nativeQuery = true)
    List<BlogEntity> findBlogList();

    /**
     * 获取对应的用户写的博客 取20条
     *
     * @param userId
     * @return
     */
    @Query(value = "select * from table_blog where userId =?1 ORDER BY blogId DESC limit 20", nativeQuery = true)
    List<BlogEntity> getBlogListByUserId(int userId);

    /**
     * 获取blogid比输入ID大的博客（不包括这个ID自己）
     * 用于请求时避免出现重复内容，节省流量
     *
     * @param blogId
     * @return
     */
    @Query(value = "select * from table_blog where blogId>?1 ORDER BY blogId DESC limit 20", nativeQuery = true)
    List<BlogEntity> getBlogListMax(int blogId);

    /**
     * 获取blogid比输入ID小的博客（不包括这个ID自己）
     * 用于请求时避免出现重复内容，节省流量
     *
     * @param blogId
     * @return
     */
    @Query(value = "select * from table_blog where blogId<?1 ORDER BY blogId DESC limit 20", nativeQuery = true)
    List<BlogEntity> getBlogListMin(int blogId);


}

