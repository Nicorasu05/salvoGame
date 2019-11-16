package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String shipType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayerID")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name="location")
    private List<String> locations = new ArrayList<>();

    public Ship() {
    }

    public Ship(String shipType, GamePlayer gamePlayer, List<String> locations) {
        this.shipType = shipType;
        this.gamePlayer = gamePlayer;
        this.locations = locations;
    }

    public long getId() {
        return id;
    }

    public String getShipType() {
        return shipType;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Map<String, Object> getShipData () {
        Map<String, Object> shipData = new HashMap<>();
        shipData.put("id", this.id);
        shipData.put("shipType", this.shipType);
        shipData.put("location", this.locations);
        return shipData;
    }
}
