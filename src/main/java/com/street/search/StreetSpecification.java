package com.street.search;

import com.street.entity.District;
import com.street.entity.Street;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Locale;

public class StreetSpecification implements Specification<Street> {
    private SearchCriteria criteria;
    public StreetSpecification() {
    }
    public StreetSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }


    @Override
    public Predicate toPredicate
            (Root<Street> root,//bang
             CriteriaQuery<?> query,//cau leng truy van
             CriteriaBuilder builder)
    {
        if(criteria==null){
            return null;
        }else{
            switch (criteria.getOperation().toLowerCase(Locale.ROOT) ){
                case ">=":
                    return builder.greaterThanOrEqualTo( root.<String> get(criteria.getKey()), criteria.getValue().toString());
                case "<=":
                    return builder.lessThanOrEqualTo( root.<String> get(criteria.getKey()), criteria.getValue().toString());
                case "=":
                case "==":
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                case "!=":
                    return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
                case "like":
                case "~":
                    return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
                case "notlike":
                case "!~":
                    return builder.notLike(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
                case "join":
                    Join<Street, District> joinTable = root.join("district");
                    return builder.like(joinTable.get("districtName"), "%" + criteria.getValue() + "%");//test
                default:
                    return null;
            }
        }
    }
}
