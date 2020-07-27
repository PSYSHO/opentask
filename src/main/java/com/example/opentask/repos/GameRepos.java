package com.example.opentask.repos;

import com.example.opentask.domain.Game;
import com.example.opentask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GameRepos extends JpaRepository<Game,Long> {
    Game findByNumber(int[] number);
}
