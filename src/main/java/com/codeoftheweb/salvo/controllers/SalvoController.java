package com.codeoftheweb.salvo.controllers;

import com.codeoftheweb.salvo.models.Game;
import com.codeoftheweb.salvo.models.Player;
import com.codeoftheweb.salvo.repositories.GameRepository;
import com.codeoftheweb.salvo.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping ("/hello")
    public String bienvenido () {
        String nombreJugador =playerRepository.findById(Long.valueOf(1)).get().getUserName();
        return "Hello: " + nombreJugador;
    }

    @RequestMapping ("/games")
    public List<Map<String, Object>> getGamesJson (){
        return gameRepository.findAll()
                             .stream()
                             .map(game -> game.getJson())
                             .collect(Collectors.toList());
    }

    @RequestMapping ("/game")
    public Map<String, Object> createAndGetGameJson () {
        Game game = new Game();
        gameRepository.save(game);

        return game.getJson();
    }
}
