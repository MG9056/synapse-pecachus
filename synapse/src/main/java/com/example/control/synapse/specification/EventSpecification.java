package com.example.control.synapse.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.Stadium;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class EventSpecification {

    public static Specification<Event> getEventsByFilters(String category, String city, Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. Category Filter (Direct field on Event)
            if (category != null && !category.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("category"), category));
            }

            // 2. City Filter (Lives inside Stadium entity!)
            if (city != null && !city.isEmpty()) {
                // "Join" Event table with Stadium table
                Join<Event, Stadium> stadiumJoin = root.join("stadium");
                predicates.add(criteriaBuilder.equal(stadiumJoin.get("city"), city));
            }

            // 3. Price Range Filter 
            // (Assuming 'Event' has a 'price' or 'startingPrice' field)
            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minPrice"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("minPrice"), maxPrice));
            }

            // Important: Remove duplicates if joins create them
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
