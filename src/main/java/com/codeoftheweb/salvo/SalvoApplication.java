package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.models.*;
import com.codeoftheweb.salvo.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SalvoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
        System.out.println("Hola");
    }

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository, SalvoRepository salvoRepository) {
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

            Ship ship1 = new Ship ("Carrier", gamePlayer1, Arrays.asList("A1", "A2", "A3","A4","A5") );
            Ship ship2 = new Ship ("Battleship", gamePlayer1, Arrays.asList("B1", "C1", "D1") );
            Ship ship3 = new Ship ("Submarine", gamePlayer1, Arrays.asList("E1", "E2", "E3") );
            Ship ship4 = new Ship ("Destroyer", gamePlayer1, Arrays.asList("F1", "G1", "H1") );
            Ship ship5 = new Ship ("Patrol Boat", gamePlayer1, Arrays.asList("D1", "D2", "D3") );

            shipRepository.saveAll(Arrays.asList(ship1,ship2,ship3,ship4,ship5));

            Salvo salvo1 = new Salvo(gamePlayer1, 1, Arrays.asList("B4","B5","B6"));
            Salvo salvo2 = new Salvo(gamePlayer2, 1, Arrays.asList("E1","H3","A2"));
            Salvo salvo3 = new Salvo(gamePlayer1, 2, Arrays.asList("B5","D5","C7"));
            Salvo salvo4 = new Salvo(gamePlayer2, 2, Arrays.asList("C5","C6"));

            salvoRepository.saveAll(Arrays.asList(salvo1,salvo2,salvo3,salvo4));
        };
    }
}

