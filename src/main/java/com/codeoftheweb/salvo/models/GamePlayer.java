package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity //Crear una tabla persona para esta clase
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "playerID")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gameID")
    private Game game;

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    List<Ship> ships;

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public long getId(){
        return id;
    }

    public GamePlayer() { this.joinDate = new Date(); };

    public GamePlayer (Player player, Game game){
        this.player = player;
        this.game = game;
        this.joinDate = new Date();
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate() { this.joinDate = joinDate;}

    public Map<String, Object> getGamePlayerData () {
        Map<String, Object> gamePlayerData = new HashMap<>();
        gamePlayerData.put("id", this.id);
        gamePlayerData.put("date", this.joinDate);
        gamePlayerData.put("player", this.player.getPlayerData());
        return gamePlayerData;
    }

}
