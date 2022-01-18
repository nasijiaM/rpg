package com.game.specifications;

import com.game.entity.Player;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PlayersTitle implements Specification<Player> {

    private String title;

    public PlayersTitle(String title) {
        this.title = title;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(this.title == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.like(root.get("title"), "%" + this.title +"%");

    }
}
