package com.piyush.InventoryManagementSystem.service.impl;

import com.piyush.InventoryManagementSystem.dto.EventDTO;
import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.Event;
import com.piyush.InventoryManagementSystem.repository.EventRepository;
import com.piyush.InventoryManagementSystem.service.EventService;
import com.piyush.InventoryManagementSystem.utility.ConverterUtility;
import com.piyush.InventoryManagementSystem.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserUtility userUtility;

    @Autowired
    ConverterUtility converterUtility;

    @Override
    public Response saveEvent(EventDTO eventDTO) {
        Event event = converterUtility.eventDtoToEvent(eventDTO);
        try {
            event.setUser(userUtility.getLoggedInUser());
            Event savedEvent = eventRepository.save(event);
            return Response.builder()
                    .status(200)
                    .event(savedEvent)
                    .message("Event Saved Sucessfully.")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.builder()
                    .status(500)
                    .message("Error in saving Event.")
                    .build();
        }
    }

    @Override
    public Response findById(Long id) {
        return null;
    }

    @Override
    public Response updateEvent(EventDTO eventDTO) {
        return null;
    }

    @Override
    public Response deleteEvent(Long id) {
        return null;
    }

    @Override
    public Response findUserEvents(Long userId) {
        return null;
    }

    @Override
    public Response getUserEvents(Long id) {
        try {
            List<Event> evenList = eventRepository.findByUserId(id);
            return Response.builder()
                    .status(200)
                    .eventList(evenList)
                    .message("Events Retrieved Sucessfully.")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.builder()
                    .status(500)
                    .message("Error in getting Events.")
                    .build();
        }
    }

    @Override
    public List<Event> findByUserIdAndStartDateAfter(Long userId, LocalDate date) {

        return eventRepository.findByUserIdAndStartDateAfter(userId, date);
    }
}
