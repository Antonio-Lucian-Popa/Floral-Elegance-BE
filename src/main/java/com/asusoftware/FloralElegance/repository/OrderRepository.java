package com.asusoftware.FloralElegance.repository;

import com.asusoftware.FloralElegance.model.Flower;
import com.asusoftware.FloralElegance.model.Order;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByStatus(String status);

    List<Order> findByFlower(Flower flower);

    @Query("""
        SELECT o.flower.id AS flowerId, COUNT(o.id) AS orderCount
        FROM Order o
        WHERE o.createdAt >= :fromDate
        GROUP BY o.flower.id
        HAVING COUNT(o.id) >= :minOrders
        ORDER BY orderCount DESC
    """)
    List<Object[]> findPopularFlowers(LocalDateTime fromDate, long minOrders);

    List<Order> findByDeliveryAddressContainingIgnoreCase(String addressPart);

    List<Order> findByUserId(UUID userId);

}