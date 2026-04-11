package com.piyush.InventoryManagementSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchApiResponse {
    private String apikey;
    private List<Match> data;

}
