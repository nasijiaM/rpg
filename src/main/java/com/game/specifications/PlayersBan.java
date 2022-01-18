package com.game.specifications;

import com.game.entity.Player;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PlayersBan implements Specification<Player> {

    private Boolean banned;

    public PlayersBan(Boolean banned) {
        this.banned = banned;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(this.banned == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.equal(root.get("banned"), this.banned);


    }
}
