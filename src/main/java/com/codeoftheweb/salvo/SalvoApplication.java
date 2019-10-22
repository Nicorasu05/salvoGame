package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.models.Game;
import com.codeoftheweb.salvo.models.GamePlayer;
import com.codeoftheweb.salvo.models.Player;
import com.codeoftheweb.salvo.repositories.GamePlayerRepository;
import com.codeoftheweb.salvo.repositories.GameRepository;
import com.codeoftheweb.salvo.repositories.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalvoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
        System.out.println("Hola");
    }

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
        return (args) -> {
            // save a couple of players
            Player player1 = new Player("nico@gmail.com");
            playerRepository.save(player1);

            Player player2 = new Player("pepe@gmail.com");
            playerRepository.save(player2);

            Game game1 =  new Game();
            gameRepository.save(game1);

            Game game2 = new Game();
            gameRepository.save(game2);

            Game game3 = new Game();
            gameRepository.save(game3);

            GamePlayer gamePlayer1 = new GamePlayer(player1, game1);
            GamePlayer gamePlayer2 = new GamePlayer(player2, game1);

            gamePlayerRepository.save(gamePlayer1);
            gamePlayerRepository.save(gamePlayer2);

        };
    }
}

