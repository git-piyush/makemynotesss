package com.piyush.InventoryManagementSystem.dto;

import com.piyush.InventoryManagementSystem.entity.ToDo;
import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class DashboardResponseDTO {
    private Long id;

    private Long totalQuestions;

      private Long totalQuestionsAddedByYou;

      private Long userBookmarked;

      private Map<String, Long> countMap;

      private List<ToDo> toDoList;

      private Long unreadFeedBackCount;

     Map<String, Long> dailyQuestionCountMap;

    Map<LocalDate, List<ToDo>> toDoMap;


        public DashboardResponseDTO(Long id,
                                    Long totalQuestions,
                                    Long totalQuestionsAddedByYou,
                                    Long userBookmarked,
                                    Map<String, Long> countMap,
                                    Long unreadFeedBackCount) {
        this.id = id;
        this.totalQuestions = totalQuestions;
        this.totalQuestionsAddedByYou = totalQuestionsAddedByYou;
        this.userBookmarked = userBookmarked;
        this.countMap = countMap;
        this.unreadFeedBackCount = unreadFeedBackCount;
    }
}
