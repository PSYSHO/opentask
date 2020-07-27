package com.example.opentask.repos;

import com.example.opentask.domain.Attempt;
import com.example.opentask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttemptRepos extends JpaRepository<Attempt,Long> {
    List<Attempt> findAllByUser(User user);
    Attempt findByUser(User user);
    Attempt findByFlag(boolean flag);
    Attempt findByFlagAndUser(User user,boolean flag);
}
