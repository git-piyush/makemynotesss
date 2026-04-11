package com.piyush.InventoryManagementSystem.repository;

import com.piyush.InventoryManagementSystem.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {

    Optional<Dashboard> findByUserId(Long userId);

}