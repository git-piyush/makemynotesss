package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.entity.Feedback;
import org.springframework.stereotype.Service;

@Service
public interface FeedBackService {

    void saveFeedback(Feedback feedback);

}
