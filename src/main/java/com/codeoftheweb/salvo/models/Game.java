package com.codeoftheweb.salvo.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity //Crear una tabla persona para esta clase
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date creationDate;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<Score> scores;

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Game() {
        this.creationDate = new Date();
    }

    public Game(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() { this.creationDate = creationDate;}

    public long getId(){
        return id;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public Map<String, Object> getJson () {
        Map<String, Object> json = new HashMap<>();

        json.put("id", this.getId());
        json.put("date", this.getCreationDate());
        json.put("gamePlayers", this.getGamePlayers().stream()
                                                    .map(gamePlayer -> gamePlayer.getGamePlayerData())
                                                    .collect(Collectors.toList()));

        return json;
    }


}
