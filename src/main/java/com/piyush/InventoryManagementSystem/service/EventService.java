package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.dto.EventDTO;
import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EventService {

    Response saveEvent(EventDTO eventDTO);

    Response findById(Long id);

    Response updateEvent(EventDTO eventDTO);

    Response deleteEvent(Long id);

    Response findUserEvents(Long userId);

    Response getUserEvents(Long id);

    List<Event> findByUserIdAndStartDateAfter(Long userId, LocalDate date);
}
