package com.piyush.InventoryManagementSystem.repository;

import com.piyush.InventoryManagementSystem.entity.MatchSubscription;
import com.piyush.InventoryManagementSystem.entity.ToDo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchSubscriptionRepository
        extends JpaRepository<MatchSubscription, Long> {

    boolean existsByMatchid(String matchId);

    @Query("SELECT t FROM MatchSubscription t WHERE t.user.id = :userId")
    List<MatchSubscription> findByUserId(@Param("userId") Long userId);

    @Transactional
    void deleteByMatchid(String id);

    MatchSubscription findByMatchid(String id);

    @Query("SELECT m.matchid FROM MatchSubscription m " +
            "WHERE m.flgMatchStarted = :started AND m.flgMatchEnded = :ended AND m.user.id=:userId")
    List<String> findMatchIdsByFlags(String started, String ended, Long userId);



//    @Modifying
//    @Transactional
//    @Query("DELETE FROM MatchSubscription m WHERE m.matchid = :matchid")
//    int deleteByMatchid(@Param("matchid") String matchid);
}
