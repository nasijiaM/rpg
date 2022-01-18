package com.game.specifications;

import com.game.entity.Player;
import com.game.entity.Race;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PlayersRace implements Specification<Player> {

    private Race race;

    public PlayersRace(Race race) {
        this.race = race;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(this.race == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.equal(root.get("race"), this.race);

    }
}
