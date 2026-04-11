package com.piyush.InventoryManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_match_subs")
public class MatchSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String matchid;

    private String strdatetimegmt;

    private String strdatetimeist;

    private LocalDateTime datetimegmt;

    private LocalDateTime datetimeist;

    private String matchType;

    private String ms;

    private String series;

    private String status;

    private String t1;

    private String t1s;

    private String t2;

    private String t2s;

    private String teams;

    private String strdate;

    private LocalDate date;

    private String strtime;

    private LocalTime time;

    private String flgMatchStarted;

    private String flgMatchEnded;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private String createdBy;

    private Date modifiedDate;

    private String modifiedBy;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key column in Todo table
    private User user;

    @PreUpdate
    @PrePersist
    public void updateTimeStamps()
    {
        String userName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if(authentication!=null){
                userName = authentication.getName();
            }else{
                userName = "By Scheduler";
            }
        }
        this.modifiedDate = new Date();
        this.modifiedBy = userName;
        if(this.createdDate == null) {
            this.createdDate = new Date();
            this.createdBy = userName;
        }
    }

}
