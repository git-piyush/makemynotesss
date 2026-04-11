package com.piyush.InventoryManagementSystem.controller;

import com.piyush.InventoryManagementSystem.dto.EventDTO;
import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.Feedback;
import com.piyush.InventoryManagementSystem.service.EventService;
import com.piyush.InventoryManagementSystem.utility.UserUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    UserUtility userUtility;

    Response response = null;


    @PostMapping("/save-event")
    public ResponseEntity<Response> saveEvent(@RequestBody EventDTO eventDTO){
        try {
            response = eventService.saveEvent(eventDTO);
        }catch (Exception e){
            return ResponseEntity.ok(Response.builder()
                    .status(500)
                    .message("Event creation Failed.")
                    .build());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-user-event")
    public ResponseEntity<Response> getUserEvents(){
        try {
            response = eventService.getUserEvents(userUtility.getLoggedInUser().getId());
        }catch (Exception e){
            return ResponseEntity.ok(Response.builder()
                    .status(500)
                    .message("Event retrieval Failed.")
                    .build());
        }
        return ResponseEntity.ok(response);
    }

}
