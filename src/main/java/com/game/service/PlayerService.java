package com.game.service;

import com.game.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();

    Page<Player> getAllPlayers(Specification<Player> specification, PageRequest pageRequest);

    void savePlayer(Player player);

    Player getPlayer(Long id);

    void deletePlayer(Long id);

    Long count(Specification<Player> specification);
}
