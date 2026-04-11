package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.dto.LiveScoreDetails;
import com.piyush.InventoryManagementSystem.dto.MatchSubscriptionDTO;
import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.MatchSubscription;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    Response saveKeys();

    Response resetKeys();

    Response increseKeyHits(String key);

    Response countHits(String key);

    String findKeysWithMinimumHits();

    Response getNextFiveMatches();

    Response saveSubscribedMatch(MatchSubscriptionDTO matchSubscriptionDTO);

    Response removeSubscribedMatch(String matchId);

    Response getSubscribedMatch();

    List<MatchSubscription> getAll();

    List<LiveScoreDetails> getLiveScore();
}
