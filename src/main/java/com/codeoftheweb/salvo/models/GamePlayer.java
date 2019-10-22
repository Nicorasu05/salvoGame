package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


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



}
