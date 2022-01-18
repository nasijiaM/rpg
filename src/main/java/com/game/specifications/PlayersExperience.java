package com.game.specifications;

import com.game.entity.Player;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PlayersExperience implements Specification<Player> {

    private Integer minExperience;
    private Integer maxExperience;

    public PlayersExperience(Integer minExperience, Integer maxExperience) {
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(this.minExperience == null && this.maxExperience == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        if (this.minExperience == null) this.minExperience = 0;
        if (this.maxExperience == null) this.maxExperience = 10000000;

        return criteriaBuilder.between(root.get("experience"), this.minExperience, this.maxExperience);

    }

}
