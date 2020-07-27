package com.example.opentask.controller;

import com.example.opentask.domain.Attempt;
import com.example.opentask.domain.Game;
import com.example.opentask.domain.Logic;
import com.example.opentask.domain.User;
import com.example.opentask.repos.AttemptRepos;
import com.example.opentask.repos.GameRepos;
import com.example.opentask.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    Attempt attempt= new Attempt();
    Game game = new Game();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttemptRepos attemptRepos;
    @Autowired
    private GameRepos gameRepos;
    private Logic logic = new Logic();

    @GetMapping
    public List<Attempt> getAllAttempt(@AuthenticationPrincipal User user) {
        return attemptRepos.findAllByUser(user);
    }

    @GetMapping("/{answer}")
    public void execute(@AuthenticationPrincipal User user, @PathVariable("answer") String answer) {
        String str;

        List<Attempt> list = attemptRepos.findAllByUser(user);
        for (Attempt attempte : list) {
            if (attempte.isFlag() == false) {
                attempt = attempte;
            }
        }
        Game game = attempt.getGame();
        int[] a = logic.parser(answer);
        logic.setNumb(game.getNumber());
        str = logic.game(a);
        attempt.setFlag(true);
        attempt.setResult(str);
        if (attempt.getResult().indexOf("Некорректное число") == -1)attemptRepos.save(attempt);
        else {
            Attempt attempt1 = new Attempt();
            attempt1.setGame(attempt.getGame());
            attempt1.setUser(attempt.getUser());
            attempt1.setFlag(false);
            attemptRepos.save(attempt1);
            attemptRepos.save(attempt);
        }
    }

    @PostMapping("/start")
    public String gameStart(@AuthenticationPrincipal User user) {
        Attempt attemptc =  new Attempt();
        attemptc = attemptRepos.findByFlag(false);
        if (attemptc == null) {
            Game game = new Game();
            Attempt attempt = new Attempt();
            attempt.setUser(user);
            logic.setNumb(logic.generator());
            game.setNumber(logic.getNumb());
            gameRepos.save(game);
            attempt.setGame(gameRepos.findByNumber(game.getNumber()));
            attempt.setFlag(false);
            attemptRepos.save(attempt);
        } else ;
        return Arrays.toString(game.getNumber()).replace("[", "").replace("]", "");
    }
}
