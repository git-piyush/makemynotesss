package com.piyush.InventoryManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.piyush.InventoryManagementSystem.entity.User;
import com.piyush.InventoryManagementSystem.enums.EventPurpose;
import com.piyush.InventoryManagementSystem.enums.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDTO {
    private Long id;

    private String eventName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String purpose;

    private String status;

}
