package com.game.repository;

import com.game.entity.Player;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>,
        PagingAndSortingRepository<Player, Integer>, JpaSpecificationExecutor<Player> {

    Player findPlayerById(Long id);

    void deletePlayerById(Long id);

}
