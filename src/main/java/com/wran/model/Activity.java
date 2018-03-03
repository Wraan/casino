package com.wran.model;

import javax.persistence.*;

@Entity
@Table(name = "ACTIVITIES")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "activities_seq_gen")
    @SequenceGenerator(name="activities_seq_gen", sequenceName = "ACTIVITIES_SEQ")
    private Long id;
    private String login;
    private int coins;
    private String game;

    public Activity(){}
    public Activity(String login, int coins, String game){
        setLogin(login);
        setCoins(coins);
        setGame(game);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
