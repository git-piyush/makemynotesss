package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.FeedBackDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public interface FeedbackDetailsService {

    List<FeedBackDetails> getUnreadFeedbackByUser(Long userId);

    Response getAllFeedbackDetails(Long userId,
                                   int page,
                                   int size,
                                   String orderBy,
                                   String order,
                                   Integer rating,
                                   LocalDateTime startDate,
                                   LocalDateTime endDate,
                                   String seen);


    String markFeedbackReadAndUnread(Long id);
}
