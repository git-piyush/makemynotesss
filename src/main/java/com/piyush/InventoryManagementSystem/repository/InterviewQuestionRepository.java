package com.piyush.InventoryManagementSystem.repository;

import com.piyush.InventoryManagementSystem.entity.InterviewQuestion;
import com.piyush.InventoryManagementSystem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Long> {

    List<InterviewQuestion> findByParentQuestionOrderByCreatedDateAsc(Question parentQuestion);

    boolean existsByParentQuestion(Question parentQuestion);

    void deleteByParentQuestion(Question parentQuestion);
}
