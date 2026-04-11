package com.piyush.InventoryManagementSystem.utility;

import com.piyush.InventoryManagementSystem.dto.MatchInfo;
import com.piyush.InventoryManagementSystem.entity.CricketdataKey;
import com.piyush.InventoryManagementSystem.entity.MatchSubscription;
import com.piyush.InventoryManagementSystem.repository.CategoryRepository;
import com.piyush.InventoryManagementSystem.repository.CricketdataKeyRepository;
import com.piyush.InventoryManagementSystem.repository.MatchSubscriptionRepository;
import com.piyush.InventoryManagementSystem.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CricketdataKeyRepository cricketdataKeyRepository;

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchSubscriptionRepository matchSubscriptionRepository;

    private final RestTemplate restTemplate = new RestTemplate();


    //@Scheduled(fixedRate = 4 * 60 * 1000) // 10 minutes in milliseconds
    public void runEveryTenMinutes() {
        System.out.println("Task executed at: " + LocalDateTime.now());
        System.out.println("Keep my db and render awake, since i am using free tier");
        categoryRepository.findAll();
        System.out.println("Thank You.");
    }

    @Scheduled(cron = "0 0 2 * * *", zone = "Asia/Kolkata")     //2 AM ist
    public void resetKeys(){
        cricketdataKeyRepository.deleteAll();
        List<CricketdataKey> cricketdataKeyList = new ArrayList<>();
        cricketdataKeyList.add(new CricketdataKey("ba8e9c81-b5c8-46db-b6e0-26e7cacbf5f6","kmrpiyush95@gmail.com","PwD74O3617.856",0));
        cricketdataKeyList.add(new CricketdataKey("5dd55e6e-6b58-4b91-8805-3dd387edf86b","admin1@yopmail.com","PwD74O6268.013",0));
        cricketdataKeyList.add(new CricketdataKey("46541834-223a-4580-b111-28a805a01624","admin2@yopmail.com","PwD74O1640.262",0));
        cricketdataKeyList.add(new CricketdataKey("aabb2fc9-9e42-4086-9b5c-21826a4c68c1","admin3@yopmail.com","PwD74O5986.382",0));
        cricketdataKeyList.add(new CricketdataKey("6afe62b2-c960-49a8-a667-959797e15f30","admin4@yopmail.com","PwD74O2759.37",0));
        cricketdataKeyList.add(new CricketdataKey("9972b41a-0393-4453-978f-2b7a27a98432","kripratima51@gmail.com","PwD7402978.601",0));
        cricketdataKeyList.add(new CricketdataKey("ac144d60-8d9f-414e-8341-1a0200b8f488","krpiyush51@yahoo.com","PwD74O6553.763",0));
        for (CricketdataKey cricketdataKey:cricketdataKeyList){
            cricketdataKeyRepository.save(cricketdataKey);
        }

        System.out.println("<=========Key Reset Done==========>");
    }

    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "0 */10 * * * *")             //every 10 min.
    public void checkIfMatchStarted(){
        String key = matchService.findKeysWithMinimumHits();
        String url = "https://api.cricapi.com/v1/match_info?apikey="+key;

        List<MatchSubscription> matchList = matchService.getAll();

        if(matchList!=null && matchList.size()>0){
            for (MatchSubscription match : matchList){
                url = url+"&id="+match.getMatchid();
                MatchInfo result = restTemplate.getForObject(url, MatchInfo.class);
                System.out.println("Match Info checked.");

                MatchInfo.Data data = result.getData();
                if(data!=null){
                    if(data.isMatchStarted()){
                        //MatchSubscription matchSubscription = matchSubscriptionRepository.findByMatchid(match.getMatchid());
                        //matchSubscription.setFlgMatchStarted("Y");
                        match.setFlgMatchStarted("Y");
                        matchSubscriptionRepository.save(match);
                    }else{
                        match.setFlgMatchStarted("N");
                        matchSubscriptionRepository.save(match);
                    }
                    if(data.isMatchEnded()){
                        MatchSubscription matchSubscription = matchSubscriptionRepository.findByMatchid(match.getMatchid());
                        matchSubscription.setFlgMatchEnded("Y");
                        matchSubscriptionRepository.save(matchSubscription);
                    }else{
                        match.setFlgMatchEnded("N");
                        matchSubscriptionRepository.save(match);
                    }
                }
            }
        }

    }





}
