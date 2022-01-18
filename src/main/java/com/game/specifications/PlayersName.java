package com.game.specifications;

import com.game.entity.Player;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PlayersName implements Specification<Player> {

    private String name;

    public PlayersName(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(this.name == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.like(root.get("name"), "%" + this.name +"%");

    }
}
