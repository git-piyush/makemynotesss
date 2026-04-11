package com.piyush.InventoryManagementSystem.service.impl;

import com.piyush.InventoryManagementSystem.entity.Feedback;
import com.piyush.InventoryManagementSystem.repository.FeedbackRepository;
import com.piyush.InventoryManagementSystem.service.FeedBackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
