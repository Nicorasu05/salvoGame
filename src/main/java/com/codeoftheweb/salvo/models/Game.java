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
    List<GamePlayer> gamePlayers;

    public Game() {
        this.creationDate = new Date();this.gamePlayers = new ArrayList<>();
    }

    public Game(Date creationDate) {
        this.creationDate = creationDate;this.gamePlayers = new ArrayList<>();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() { this.creationDate = creationDate;}

    public long getId(){
        return id;
    }

    public Map<String, Object> getJson () {
        Map<String, Object> json = new HashMap<>();

        json.put("id", this.id);
        json.put("date", this.creationDate);
        json.put("gamePlayers", this.gamePlayers.stream()
                                                .map(gamePlayer -> gamePlayer.getGamePlayerData())
                                                .collect(Collectors.toList()));

        return json;
    }


}
