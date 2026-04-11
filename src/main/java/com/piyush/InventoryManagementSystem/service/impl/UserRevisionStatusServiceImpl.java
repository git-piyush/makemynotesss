package com.piyush.InventoryManagementSystem.service.impl;

import com.piyush.InventoryManagementSystem.entity.Question;
import com.piyush.InventoryManagementSystem.entity.UserRevisionStatus;
import com.piyush.InventoryManagementSystem.exceptions.NotFoundException;
import com.piyush.InventoryManagementSystem.repository.UserRevisionStatusRepository;
import com.piyush.InventoryManagementSystem.service.UserRevisionStatusService;
import com.piyush.InventoryManagementSystem.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserRevisionStatusServiceImpl implements UserRevisionStatusService {

    @Autowired
    private UserRevisionStatusRepository userRevisionStatusRepository;

    @Autowired
    private UserUtility userUtility;

    @Override
    public void saveRevisionStatus(Long qid, Question question){

       List<UserRevisionStatus> revisionStatusList = userRevisionStatusRepository.findByQidAndUseridAndCreatedDate(qid, userUtility.getLoggedInUser().getId(), new Date());

       if(revisionStatusList!=null && revisionStatusList.size()==0){
           Long maxCount = getMaxCountForToday(userUtility.getLoggedInUser().getId());
           UserRevisionStatus revisionStatus = new UserRevisionStatus();
           revisionStatus.setCount(maxCount+1);
           revisionStatus.setQid(qid);
           revisionStatus.setType(question.getType());
           revisionStatus.setUserid(userUtility.getLoggedInUser().getId());
           userRevisionStatusRepository.save(revisionStatus);
       }


    }


    public Long getMaxCountForToday(Long userId){

        LocalDate today = LocalDate.now();

        Date start = Date.from(
                today.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        Date end = Date.from(
                today.atTime(23, 59, 59)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );

        Long result = userRevisionStatusRepository.findMaxCountByUseridAndDate(userId, start, end);

        return result != null ? result : 0L;
    }

}
