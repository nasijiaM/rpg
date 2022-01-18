package com.game.service;

import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Transactional
    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Transactional
    @Override
    public Page<Player> getAllPlayers(Specification<Player> specification, PageRequest pageRequest) {
        return playerRepository.findAll(specification, pageRequest);
    }

    @Transactional
    @Override
    public void savePlayer(Player player) {
        int level = (int) ((Math.sqrt((200 * player.getExperience()) + 2500) - 50) / 100);
        player.setLevel(level);

        int until = 50 * (level + 1) * (level + 2) - player.getExperience();
        player.setUntilNextLevel(until);

        playerRepository.save(player);
    }

    @Transactional
    @Override
    public Player getPlayer(Long id) {

        return playerRepository.findPlayerById(id);
    }

    @Transactional
    @Override
    public void deletePlayer(Long id) {
        playerRepository.deletePlayerById(id);
    }

    @Transactional
    @Override
    public Long count (Specification<Player> specification) {
        return playerRepository.count(specification);
    }
}
