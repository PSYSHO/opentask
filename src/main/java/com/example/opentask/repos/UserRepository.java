package com.example.opentask.repos;

import com.example.opentask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAll();
    User findById(int i);

    //@Query("SELECT A from usr A WHERE A.username=:username")
    User findByUsername(@Param("username") String username);

}
