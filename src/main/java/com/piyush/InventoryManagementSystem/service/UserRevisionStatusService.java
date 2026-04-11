package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.entity.Question;
import org.springframework.stereotype.Service;

@Service
public interface UserRevisionStatusService {
    void saveRevisionStatus(Long qid, Question question);
}
