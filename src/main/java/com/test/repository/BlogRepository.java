package com.test.repository;

/**
 * Created by egguncle on 17-1-11.
 */

import com.test.model.TableBlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<TableBlogEntity, Integer> {
//    List<TableBlogEntity> findAll();
//    TableBlogEntity findById(int id);


//    @Select("select max(blogId) from table_blog")
//    int blogId();

}