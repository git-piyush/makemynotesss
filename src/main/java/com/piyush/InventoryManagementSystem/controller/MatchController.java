package com.piyush.InventoryManagementSystem.controller;

import com.piyush.InventoryManagementSystem.dto.LiveScoreDetails;
import com.piyush.InventoryManagementSystem.dto.MatchSubscriptionDTO;
import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.repository.CricketdataKeyRepository;
import com.piyush.InventoryManagementSystem.service.MatchService;
import com.piyush.InventoryManagementSystem.utility.UserUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MatchController {

    @Autowired
    private CricketdataKeyRepository cricketdataKeyRepository;

    @Autowired
    private UserUtility userUtility;

    @Autowired
    private MatchService matchService;

    @PostMapping("/key/save")
    public ResponseEntity<Response> saveKeys(){
        return ResponseEntity.ok(matchService.saveKeys());
    }

    @PostMapping("/key/reset")
    public ResponseEntity<Response> resetKeys(){
        return ResponseEntity.ok(matchService.resetKeys());
    }

    @GetMapping("/key/increasehits/{key}")
    public ResponseEntity<Response> increseKeyHits(@PathVariable String key){
        return ResponseEntity.ok(matchService.increseKeyHits(key));
    }

    @GetMapping("/key/counthits/{key}")
    public ResponseEntity<Response> countHits(String key){
        return ResponseEntity.ok(matchService.countHits(key));
    }

    @GetMapping("/key/keyminimumhits")
    public ResponseEntity<Response> findKeysWithMinimumHits(){
        return ResponseEntity.ok(Response.builder()
                        .status(200)
                        .message("Key with minimum hits")
                        .cKey(matchService.findKeysWithMinimumHits())
                .build());
    }

    @GetMapping("/match/next-five-match")
    public ResponseEntity<Response> getNextFiveMatches(){
        return ResponseEntity.ok(matchService.getNextFiveMatches());
    }

    @PostMapping("/match/save-subscribedmatch")
    public ResponseEntity<?> saveSubscription(
            @RequestBody MatchSubscriptionDTO request) {

        return ResponseEntity.ok(matchService.saveSubscribedMatch(request));
    }

    @GetMapping("/match/live-score")
    public ResponseEntity<List<LiveScoreDetails>> getLiveScore() {
        List<LiveScoreDetails> liveScoreDetails = matchService.getLiveScore();
        return ResponseEntity.ok(liveScoreDetails);
    }

    @GetMapping("/match/subscribed-match")
    public ResponseEntity<Response> getSubscribedMatch(){
        return ResponseEntity.ok(matchService.getSubscribedMatch());
    }

    @DeleteMapping("/delete-match")
    public ResponseEntity<Response> deleteMatch(@RequestBody MatchSubscriptionDTO request){
        return ResponseEntity.ok(matchService.removeSubscribedMatch(request.getId()));
    }

}
