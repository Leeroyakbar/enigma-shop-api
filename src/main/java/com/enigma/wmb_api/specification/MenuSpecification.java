package com.enigma.wmb_api.specification;

import com.enigma.wmb_api.dto.request.SearchMenuRequest;
import com.enigma.wmb_api.entity.Menu;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MenuSpecification {
    public static Specification<Menu> getSpecification(SearchMenuRequest request){
        List<Predicate> predicates = new ArrayList<>();
        return (root, query, criteriaBuilder) ->{
            if(request.getName() != null){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + request.getName().toLowerCase() + "%"
                        )
                );
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
