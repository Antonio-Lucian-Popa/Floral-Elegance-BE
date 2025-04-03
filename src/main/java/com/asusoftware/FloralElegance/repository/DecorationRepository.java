package com.asusoftware.FloralElegance.repository;


import com.asusoftware.FloralElegance.model.Decoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DecorationRepository extends JpaRepository<Decoration, UUID> {
}