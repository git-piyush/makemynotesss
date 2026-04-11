package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.dto.DashboardResponseDTO;
import com.piyush.InventoryManagementSystem.entity.Dashboard;
import org.springframework.stereotype.Service;

@Service
public interface DashboardService {
    public Dashboard getDashboardData();
}
