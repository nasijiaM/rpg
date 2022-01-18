package com.game.specifications;

import com.game.entity.Player;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PlayersLevel implements Specification<Player> {

    private Integer minLevel;
    private Integer maxLevel;

    public PlayersLevel(Integer minLevel, Integer maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(this.minLevel == null && this.maxLevel == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        if (this.minLevel == null) this.minLevel = 0;
        if (this.maxLevel == null) this.maxLevel = Integer.MAX_VALUE;

        return criteriaBuilder.between(root.get("level"), this.minLevel, this.maxLevel);

    }
}
