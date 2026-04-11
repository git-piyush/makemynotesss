package com.piyush.InventoryManagementSystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Match {
    private String id;
    private String dateTimeGMT;
    private String dateTimeIST;
    private String matchType;
    private String status;
    private String ms;
    private String t1;
    private String t2;
    private String t1s;
    private String t2s;
    private String t1img;
    private String t2img;
    private String series;
    private String teams;
    private String strdate;
    private LocalDate date;
    private String strtime;
    private LocalTime time;
}
