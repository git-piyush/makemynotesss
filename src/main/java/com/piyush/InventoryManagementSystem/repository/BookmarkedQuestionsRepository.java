package com.piyush.InventoryManagementSystem.repository;

import com.piyush.InventoryManagementSystem.entity.BookmarkedQuestions;
import com.piyush.InventoryManagementSystem.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookmarkedQuestionsRepository extends JpaRepository<BookmarkedQuestions, Long> {

    Optional<BookmarkedQuestions> findByQidAndUserid(Long qid, Long userid);

    Boolean existsByQidAndUserid(Long qid, Long userid);


    void deleteAllByQid(Long id);
}
