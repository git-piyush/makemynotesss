package com.piyush.InventoryManagementSystem.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.piyush.InventoryManagementSystem.dto.*;
import com.piyush.InventoryManagementSystem.entity.CricketdataKey;
import com.piyush.InventoryManagementSystem.entity.MatchSubscription;
import com.piyush.InventoryManagementSystem.repository.CricketdataKeyRepository;
import com.piyush.InventoryManagementSystem.repository.MatchSubscriptionRepository;
import com.piyush.InventoryManagementSystem.service.MatchService;
import com.piyush.InventoryManagementSystem.utility.UserUtility;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private CricketdataKeyRepository cricketdataKeyRepository;

    @Autowired
    private MatchSubscriptionRepository matchSubscriptionRepository;

    @Autowired
    private UserUtility userUtility;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ModelMapper mapper;

    @Override
    public Response saveKeys() {
        List<CricketdataKey> cricketdataKeyList = new ArrayList<>();
        cricketdataKeyList.add(new CricketdataKey("ba8e9c81-b5c8-46db-b6e0-26e7cacbf5f6","kmrpiyush95@gmail.com","PwD74O3617.856",0));
        cricketdataKeyList.add(new CricketdataKey("5dd55e6e-6b58-4b91-8805-3dd387edf86b","admin1@yopmail.com","PwD74O6268.013",0));
        cricketdataKeyList.add(new CricketdataKey("46541834-223a-4580-b111-28a805a01624","admin2@yopmail.com","PwD74O1640.262",0));
        cricketdataKeyList.add(new CricketdataKey("aabb2fc9-9e42-4086-9b5c-21826a4c68c1","admin3@yopmail.com","PwD74O5986.382",0));
        cricketdataKeyList.add(new CricketdataKey("6afe62b2-c960-49a8-a667-959797e15f30","admin4@yopmail.com","PwD74O2759.37",0));
        cricketdataKeyList.add(new CricketdataKey("2b38c53f-2b91-453a-8d85-a68bd72a168b","admin5@yopmail.com","PwD74O5843.576",0));
        try {
            for (CricketdataKey cricketdataKey:cricketdataKeyList){
                cricketdataKeyRepository.save(cricketdataKey);
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Response.builder()
                    .status(500)
                    .message("Exception in saving Keys.")
                    .build();
        }
        return  Response.builder()
                .status(200)
                .message("Keys has been saved Successfully.")
                .build();

    }

    @Override
    public Response resetKeys() {
        cricketdataKeyRepository.deleteAll();
        List<CricketdataKey> cricketdataKeyList = new ArrayList<>();
        cricketdataKeyList.add(new CricketdataKey("ba8e9c81-b5c8-46db-b6e0-26e7cacbf5f6","kmrpiyush95@gmail.com","PwD74O3617.856",0));
        cricketdataKeyList.add(new CricketdataKey("5dd55e6e-6b58-4b91-8805-3dd387edf86b","admin1@yopmail.com","PwD74O6268.013",0));
        cricketdataKeyList.add(new CricketdataKey("46541834-223a-4580-b111-28a805a01624","admin2@yopmail.com","PwD74O1640.262",0));
        cricketdataKeyList.add(new CricketdataKey("aabb2fc9-9e42-4086-9b5c-21826a4c68c1","admin3@yopmail.com","PwD74O5986.382",0));
        cricketdataKeyList.add(new CricketdataKey("6afe62b2-c960-49a8-a667-959797e15f30","admin4@yopmail.com","PwD74O2759.37",0));
        cricketdataKeyList.add(new CricketdataKey("9972b41a-0393-4453-978f-2b7a27a98432","kripratima51@gmail.com","PwD7402978.601",0));
        cricketdataKeyList.add(new CricketdataKey("ac144d60-8d9f-414e-8341-1a0200b8f488","krpiyush51@yahoo.com","PwD74O6553.763",0));
        try {
            for (CricketdataKey cricketdataKey:cricketdataKeyList){
                cricketdataKeyRepository.save(cricketdataKey);
            }
        }catch(Exception e){
            e.printStackTrace();
            return Response.builder()
                    .status(500)
                    .message("Exception in Resetting Keys.")
                    .build();
        }
        return Response.builder()
                .status(200)
                .message("Keys has been reset sucessfully.")
                .build();
    }

    @Override
    public Response increseKeyHits(String key) {
        try{
            CricketdataKey cricketdataKey = cricketdataKeyRepository.findByKey(key);
            cricketdataKey.setHits(cricketdataKey.getHits()+1);
            cricketdataKeyRepository.save(cricketdataKey);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.builder()
                    .status(500)
                    .message("Exception Increasing key hits.")
                    .build();
        }
        return Response.builder()
                .status(500)
                .message("Key hits increase.")
                .build();
    }

    @Override
    public Response countHits(String key){
        CricketdataKey cricketdataKey = cricketdataKeyRepository.findByKey(key);
        Integer hits = cricketdataKey.getHits();
        return Response.builder()
                .status(200)
                .keyHits(hits)
                .message("Hits retrieved sucesfully")
                .build();
    }

    @Override
    public String findKeysWithMinimumHits() {
        List<String> keys = cricketdataKeyRepository.findKeysWithMinimumHits();
        String key = keys.isEmpty() ? null : keys.get(0);
        increseKeyHits(key);
        return key;
    }

    @Override
    public Response getNextFiveMatches() {
        String key = findKeysWithMinimumHits();
        String url = "https://api.cricapi.com/v1/cricScore?apikey="+key;
        MatchApiResponse result = restTemplate.getForObject(url, MatchApiResponse.class);

        try {
            if(result.getData()!=null && result.getData().size()>0){
                List<Match> allMatch = result.getData();
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                LocalDateTime now = LocalDateTime.now();

                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                List<Match> nextFiveMatch = allMatch.stream()
                        .filter(match -> {
                            LocalDateTime matchDate = LocalDateTime.parse(match.getDateTimeGMT(), formatter);
                            return (!matchDate.isBefore(now) &&
                                    (match.getMatchType().contains("t20") ||
                                            match.getSeries().contains("Indian Premier League")));
                        })
                        .sorted((m1, m2) -> {
                            LocalDateTime d1 = LocalDateTime.parse(m1.getDateTimeGMT(), formatter);
                            LocalDateTime d2 = LocalDateTime.parse(m2.getDateTimeGMT(), formatter);
                            return d1.compareTo(d2);
                        })
                        .limit(5)
                        .peek(match -> {
                            LocalDateTime gmtTime = LocalDateTime.parse(match.getDateTimeGMT(), formatter);

                            ZonedDateTime gmtZoned = gmtTime.atZone(ZoneId.of("UTC"));
                            ZonedDateTime istZoned = gmtZoned.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));

                            String istTime = istZoned.format(formatter1);
                            match.setDateTimeIST(istTime);
                        })
                        .collect(Collectors.toList());

                return Response.builder()
                        .status(200)
                        .matches(nextFiveMatch)
                        .message("Next Five matched retrieved")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            increseKeyHits(key);
            return Response.builder()
                    .status(200)
                    .message("Exception in match retrieval matched retrieved")
                    .build();
        }
        return null;
    }

    @Override
    public Response getSubscribedMatch(){
        List<MatchSubscription> matchSubscriptions = matchSubscriptionRepository.findByUserId(userUtility.getLoggedInUser().getId());
        if(matchSubscriptions==null){
            return Response
                    .builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("No subscription yet.")
                    .build();
        }
        //convert matchSubscriptions to Match list
        List<Match> matchList = mapper.map(
                matchSubscriptions,
                new TypeToken<List<Match>>() {}.getType()
        );
        List<LiveScoreDetails> liveScoreDetails = getLiveScore();
        return Response
                .builder()
                .status(HttpStatus.OK.value())
                .message("Match details retrieved sucessully.")
                .liveScoreDetails(liveScoreDetails)
                .matches(matchList)
                .build();
    }

    @Override
    public List<MatchSubscription> getAll() {
        return matchSubscriptionRepository.findAll();
    }

    @Override
    public List<LiveScoreDetails> getLiveScore() {
        List<String> matchSubscriptionList = matchSubscriptionRepository.findMatchIdsByFlags("Y","N", userUtility.getLoggedInUser().getId());
        //List<MatchSubscription> matchSubscriptionList =  matchSubscriptionRepository.findByUserId(userUtility.getLoggedInUser().getId());
        List<LiveScoreDetails> liveScoreDetailsList = new ArrayList<>();
        for(String matchId : matchSubscriptionList) {
            //String matchId = matchSubscription.getMatchid();
            LiveScoreDetails liveScore = new LiveScoreDetails();
            String key = findKeysWithMinimumHits();
            String url = "https://api.cricapi.com/v1/match_info?apikey=" + key +"&id=" + matchId;
            MatchInfo result = restTemplate.getForObject(url, MatchInfo.class);
            liveScore.setName(result.getData().getName());
            liveScore.setMatchid(result.getData().getId());
            liveScore.setT1(result.getData().getScore().get(0).getInning());
            liveScore.setT1s(result.getData().getScore().get(0).getR());
            liveScore.setT1w(result.getData().getScore().get(0).getW());
            liveScore.setT1o(result.getData().getScore().get(0).getO());
            liveScore.setStatus(result.getData().getStatus());
            if(result.getData().getScore().size()==2){
                liveScore.setT2s(result.getData().getScore().get(1).getR());
                liveScore.setT2w(result.getData().getScore().get(1).getW());
                liveScore.setT2o(result.getData().getScore().get(1).getO());
                liveScore.setT2(result.getData().getScore().get(1).getInning());
            }
            liveScoreDetailsList.add(liveScore);
        }
        return liveScoreDetailsList;
    }

    @Override
    public Response saveSubscribedMatch(MatchSubscriptionDTO matchSubscriptionDTO) {

        if (matchSubscriptionRepository.existsByMatchid(matchSubscriptionDTO.getId())) {
            return Response.builder()
                    .status(200)
                    .message("Already present in subscription.")
                    .build();
        }

        String strDateTimeGMT = matchSubscriptionDTO.getDateTimeGMT();
        LocalDate localDate = null;
        LocalTime localTime = null;

        try{
            LocalDateTime localDateTime = LocalDateTime.parse(strDateTimeGMT);
            localDate = localDateTime.toLocalDate();
            localTime = localDateTime.toLocalTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime gmtTime = LocalDateTime.parse(matchSubscriptionDTO.getDateTimeGMT(), formatter);

        ZonedDateTime gmtZoned = gmtTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime istZoned = gmtZoned.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));

        String istTime = istZoned.format(formatter1);
        LocalDateTime datetimeist = LocalDateTime.parse(istTime.replace(" ", "T"));




        MatchSubscription subscription = MatchSubscription.builder()
                .matchid(matchSubscriptionDTO.getId())
                .strdatetimegmt(matchSubscriptionDTO.getDateTimeGMT())
                .datetimegmt(LocalDateTime.parse(matchSubscriptionDTO.getDateTimeGMT()))
                .strdatetimeist(istTime)
                .datetimeist(datetimeist)
                .matchType(matchSubscriptionDTO.getMatchType())
                .ms(matchSubscriptionDTO.getMs())
                .series(matchSubscriptionDTO.getSeries())
                .status(matchSubscriptionDTO.getStatus())
                .t1(matchSubscriptionDTO.getT1())
                .t1s(matchSubscriptionDTO.getT1s())
                .t2(matchSubscriptionDTO.getT2())
                .t2s(matchSubscriptionDTO.getT2s())
                .teams(matchSubscriptionDTO.getTeams())
                .strdate(matchSubscriptionDTO.getDate())
                .date(localDate)
                .strtime(matchSubscriptionDTO.getTime())
                .time(localTime)
                .teams(matchSubscriptionDTO.getT1()+" vs "+matchSubscriptionDTO.getT2())
                .user(userUtility.getLoggedInUser())
                .flgMatchStarted("N")
                .flgMatchEnded("N")
                .build();

        try {
            MatchSubscription subscribedMatch = matchSubscriptionRepository.save(subscription);
            return Response.builder()
                    .status(200)
                    .message("Match has been added in your subscription list for live score.")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.builder()
                    .status(500)
                    .message("Exception while saving.")
                    .build();
        }
    }

    @Override
    public Response removeSubscribedMatch(String matchId) {
        try{
            matchSubscriptionRepository.deleteByMatchid(matchId);
            return Response.builder()
                    .status(200)
                    .message("Match has been removed from your subscription list for live score.")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.builder()
                    .status(200)
                    .message("Exception in removing subscription.")
                    .build();
        }
    }
}
