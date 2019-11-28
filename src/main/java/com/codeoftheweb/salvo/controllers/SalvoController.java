package com.codeoftheweb.salvo.controllers;

import com.codeoftheweb.salvo.models.Game;
import com.codeoftheweb.salvo.models.GamePlayer;
import com.codeoftheweb.salvo.models.Player;
import com.codeoftheweb.salvo.repositories.GamePlayerRepository;
import com.codeoftheweb.salvo.repositories.GameRepository;
import com.codeoftheweb.salvo.repositories.PlayerRepository;
import com.codeoftheweb.salvo.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @RequestMapping ("/hello")
    public String bienvenido () {
        String nombreJugador =playerRepository.findById(Long.valueOf(1)).get().getUserName();
        return "Hello: " + nombreJugador;
    }

    @RequestMapping ("/games")
    public Map<String, Object> getGamesJson (){

        Map<String, Object> dto =   new LinkedHashMap<>();

        dto.put("player",   "Guest");

        dto.put("games",    gameRepository.findAll()
                .stream()
                .map(game -> game.getJson())
                .collect(Collectors.toList()));

        return dto;
    }

    @RequestMapping ("/game_view/{gpid}")
    public Map<String, Object> createAndGetGameJson (@PathVariable Long gpid) {
        GamePlayer gamePlayer = gamePlayerRepository.findById(gpid).orElse(null);
        Map<String, Object> gameViewDTO = new HashMap<>();
        gameViewDTO.put("id", gamePlayer.getGame().getId());
        gameViewDTO.put("created", gamePlayer.getGame().getCreationDate());
        gameViewDTO.put("gamePlayers", gamePlayer.getGame().getGamePlayers().stream().map(gp -> gp.getGamePlayerData()).collect(Collectors.toList()));
        gameViewDTO.put("ships", gamePlayer.getShips().stream().map (ship -> ship.getShipData()).collect(Collectors.toList()));
        gameViewDTO.put("salvoes", gamePlayer.getGame().getGamePlayers().stream().flatMap(gp -> gp.getSalvoes().stream().map(salvo -> salvo.getSalvoData())).collect(Collectors.toList()));

return gameViewDTO ;
    }

    @RequestMapping ("/ships/{shipid}")
    public Map<String, Object> getship (@PathVariable Long shipid){
        return shipRepository.findById(shipid).get().getShipData();

    }




}
