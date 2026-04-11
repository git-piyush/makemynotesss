package com.piyush.InventoryManagementSystem.repository;

import com.piyush.InventoryManagementSystem.entity.CricketdataKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CricketdataKeyRepository extends JpaRepository<CricketdataKey, Long> {

    CricketdataKey findByKey(String key);

    @Query("SELECT c.key FROM CricketdataKey c WHERE c.hits = " +
            "(SELECT MIN(c2.hits) FROM CricketdataKey c2)")
    List<String> findKeysWithMinimumHits();
}
