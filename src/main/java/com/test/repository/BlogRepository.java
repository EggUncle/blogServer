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
 */


@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {

    @Query(value = "select * from table_blog where userId =?1",nativeQuery = true)
    List<BlogEntity> getBlogListByUserId(int userId);

}

