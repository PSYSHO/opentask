package com.example.opentask.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Attempt {

    private Long id;
    private String result;
    private Game game;
    private User user;
    private boolean flag;


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    @NotNull
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
