package com.asusoftware.FloralElegance.repository;


import com.asusoftware.FloralElegance.model.Flower;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, UUID> {

    List<Flower> findByTag(String tag);

    @Query("""
        SELECT f FROM Flower f
        WHERE f.price >= :minPrice AND f.price <= :maxPrice
        ORDER BY f.price ASC
    """)
    List<Flower> findByPriceRange(double minPrice, double maxPrice);

    @Query("""
    SELECT f FROM Flower f
    WHERE f.id IN (
        SELECT o.flower.id FROM Order o
        WHERE o.createdAt >= :startDate
        GROUP BY o.flower.id
        ORDER BY COUNT(o) DESC
    )
""")
    List<Flower> findPopularFlowersFromDate(@Param("startDate") LocalDateTime startDate);

}
