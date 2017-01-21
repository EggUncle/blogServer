package com.test.repository;


import com.test.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by egguncle on 17-1-14.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {




}
