package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exception.InvalidDataException;
import com.game.exception.PlayerNotFoundException;
import com.game.service.PlayerService;
import com.game.specifications.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class MyController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public List<Player> getPlayersList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "title", required = false)  String title,
            @RequestParam(value = "race", required = false)  Race race,
            @RequestParam(value = "profession", required = false)  Profession profession,
            @RequestParam(value = "after", required = false)  Long after,
            @RequestParam(value = "before", required = false)  Long before,
            @RequestParam(value = "banned", required = false)  Boolean banned,
            @RequestParam(value = "minExperience", required = false)  Integer minExperience,
            @RequestParam(value = "maxExperience", required = false)  Integer maxExperience,
            @RequestParam(value = "minLevel", required = false)  Integer minLevel,
            @RequestParam(value = "maxLevel", required = false)  Integer maxLevel,
            @RequestParam(value = "order", required = false)  Optional<PlayerOrder> order,
            @RequestParam(value = "pageNumber", required = false)  Optional<Integer> pageNumber,
            @RequestParam(value = "pageSize", required = false)  Optional<Integer> pageSize) {

        Specification<Player> specification = Specification.where(new PlayersName(name))
                .and(new PlayersTitle(title))
                .and(new PlayersRace(race))
                .and(new PlayersProfession(profession))
                .and(new PlayersBirthday(after, before))
                .and(new PlayersBan(banned))
                .and(new PlayersExperience(minExperience, maxExperience))
                .and(new PlayersLevel(minLevel, maxLevel));

        PageRequest pageRequest = PageRequest.of(pageNumber.orElse(0),
                pageSize.orElse(3),
                Sort.Direction.ASC,
                order.orElse(PlayerOrder.ID).getFieldName());

        return playerService.getAllPlayers(specification, pageRequest).stream().collect(Collectors.toList());
    }

    @GetMapping("/players/count")
    public Long getPlayersCount(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "title", required = false)  String title,
            @RequestParam(value = "race", required = false)  Race race,
            @RequestParam(value = "profession", required = false)  Profession profession,
            @RequestParam(value = "after", required = false)  Long after,
            @RequestParam(value = "before", required = false)  Long before,
            @RequestParam(value = "banned", required = false)  Boolean banned,
            @RequestParam(value = "minExperience", required = false)  Integer minExperience,
            @RequestParam(value = "maxExperience", required = false)  Integer maxExperience,
            @RequestParam(value = "minLevel", required = false)  Integer minLevel,
            @RequestParam(value = "maxLevel", required = false)  Integer maxLevel) {

        Specification<Player> specification = Specification.where(new PlayersName(name))
                .and(new PlayersTitle(title))
                .and(new PlayersRace(race))
                .and(new PlayersProfession(profession))
                .and(new PlayersBirthday(after, before))
                .and(new PlayersBan(banned))
                .and(new PlayersExperience(minExperience, maxExperience))
                .and(new PlayersLevel(minLevel, maxLevel));

        return playerService.count(specification);
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable Long id) {
        if(id < 1) {
            throw new InvalidDataException();
        }
        Player player = playerService.getPlayer(id);

        if (player == null) {
            throw new PlayerNotFoundException();
        }


        return player;
    }

    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player) {

        if(player.getName() ==null || player.getName().isEmpty() || player.getName().equals("") ||
        player.getTitle() == null ||
        player.getRace() == null ||
        player.getProfession() == null ||
        player.getBirthday() == null ||
        player.getExperience() == null ||
        player.getName().length() > 12 ||
        player.getTitle().length() > 30 ||
        player.getExperience() > 10000000 || player.getExperience() < 0 ||
        player.getBirthday().getTime() < 0 ||
        player.getBirthday().getTime() < 946674000000L ||
        player.getBirthday().getTime() > 32503669200000L)
        {
            throw new InvalidDataException();
        }

        playerService.savePlayer(player);
        return player;
    }

    @PostMapping("/players/{id}")
    public Player updatePlayer(@RequestBody Player player, @PathVariable Long id) {
        if(id < 1) {
            throw new InvalidDataException();
        }
        Player updatePlayer = playerService.getPlayer(id);
        if (updatePlayer == null) {
            throw new PlayerNotFoundException();
        }
        if (player.getName() == null &&
        player.getTitle() == null &&
        player.getRace() == null &&
        player.getProfession() == null &&
        player.getBirthday() == null &&
        player.getBanned() == null &&
        player.getExperience() == null) {
            return updatePlayer;
        }
        if (player.getExperience() != null) {
            if (player.getExperience() > 10000000 || player.getExperience() < 0) {
                throw new InvalidDataException();
            }
        }
        if (player.getBirthday() != null) {
                 if(player.getBirthday().getTime() < 0 ||
                    player.getBirthday().getTime() < 946674000000L ||
                    player.getBirthday().getTime() > 32503669200000L) {
                      throw new InvalidDataException();
                 }
        }

        if (player.getName() != null) updatePlayer.setName(player.getName());
        if (player.getTitle() != null) updatePlayer.setTitle(player.getTitle());
        if (player.getRace() != null) updatePlayer.setRace(player.getRace());
        if (player.getProfession() != null) updatePlayer.setProfession(player.getProfession());
        if (player.getBirthday() != null) {
            updatePlayer.setBirthday(player.getBirthday());
        }
        if (player.getBanned() != null) updatePlayer.setBanned(player.getBanned());
        if (player.getExperience() != null){
            updatePlayer.setExperience(player.getExperience());
        }

        playerService.savePlayer(updatePlayer);

        return updatePlayer;
    }

    @DeleteMapping("players/{id}")
    public void deletePlayer(@PathVariable Long id) {
        if (id < 1) {
            throw new InvalidDataException();
        }
        Player player = playerService.getPlayer(id);

        if (player == null) {
            throw new PlayerNotFoundException();
        }

            playerService.deletePlayer(id);
    }
}
