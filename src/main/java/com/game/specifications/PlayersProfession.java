package com.game.specifications;

import com.game.entity.Player;
import com.game.entity.Profession;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PlayersProfession implements Specification<Player> {

    private Profession profession;

    public PlayersProfession(Profession profession) {
        this.profession = profession;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(this.profession == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.equal(root.get("profession"), this.profession);

    }
}
