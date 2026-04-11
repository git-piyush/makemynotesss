package com.piyush.InventoryManagementSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchInfo {

    private String apikey;
    private Data data;
    private String status;
    private Info info;

    // Getters and Setters

    @lombok.Data
    public static class Data {
        private String id;
        private String name;
        private String matchType;
        private String status;
        private String venue;
        private String date;
        private String dateTimeGMT;
        private List<String> teams;
        private List<TeamInfo> teamInfo;
        private String series_id;
        private List<Score> score;
        private boolean fantasyEnabled;
        private boolean bbbEnabled;
        private boolean hasSquad;
        private boolean matchStarted;
        private boolean matchEnded;

        // Getters and Setters
    }

    @lombok.Data
    public static class TeamInfo {
        private String name;
        private String shortname;
        private String img;

        // Getters and Setters
    }

    @lombok.Data
    public static class Score{
        private String r;
        private String w;
        private String o;
        private String inning;
    }

    @lombok.Data
    public static class Info {
        private int hitsToday;
        private int hitsUsed;
        private int hitsLimit;
        private int credits;
        private int server;
        private double queryTime;
        private int s;
        private int cache;

        // Getters and Setters
    }
}

