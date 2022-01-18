package com.game.specifications;

import com.game.entity.Player;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class PlayersBirthday implements Specification<Player> {

    private Long after;
    private Long before;

    public PlayersBirthday(Long after, Long before) {
        this.after = after;
        this.before = before;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(this.after == null && this.before == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        if (this.after == null) this.after = 946674000000L;
        if (this.before == null) this.before = 32503669200000L;


        return criteriaBuilder.between(root.<Date>get("birthday"), new Date(after), new Date(before));

    }
}
