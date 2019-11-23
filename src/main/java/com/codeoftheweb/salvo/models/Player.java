package com.codeoftheweb.salvo.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity //Crear una tabla persona para esta clase
public class Player {

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String userName;

    public Player() { }

    public Player(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public long getId(){
        return id;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public Map<String, Object> getPlayerData() {
        Map<String, Object> playerData = new HashMap<>();
        playerData.put("id", this.getId());
        playerData.put("user", this.getUserName());
        return playerData;
    }

}
