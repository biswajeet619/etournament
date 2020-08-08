package com.etournament.proj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class Match {

    @Id
    @GeneratedValue
    private Long matchId;
    private Double matchFees;
    private Double matchPrize;
    private String matchName;
    private String squadCriteria;
    private String matchPP;
    private LocalDateTime matchDate;
    private String matchMap;
    private String matchPhotoImg;

    public Match() {
    }

    public Match(Double matchFees, Double matchPrize, String matchName, String squadCriteria, String matchPP, LocalDateTime matchDate, String matchMap, String matchPhotoImg) {
        this.matchFees = matchFees;
        this.matchPrize = matchPrize;
        this.matchName = matchName;
        this.squadCriteria = squadCriteria;
        this.matchPP = matchPP;
        this.matchDate = matchDate;
        this.matchMap = matchMap;
        this.matchPhotoImg = matchPhotoImg;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Double getMatchFees() {
        return matchFees;
    }

    public void setMatchFees(Double matchFees) {
        this.matchFees = matchFees;
    }

    public Double getMatchPrize() {
        return matchPrize;
    }

    public void setMatchPrize(Double matchPrize) {
        this.matchPrize = matchPrize;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getSquadCriteria() {
        return squadCriteria;
    }

    public void setSquadCriteria(String squadCriteria) {
        this.squadCriteria = squadCriteria;
    }

    public String getMatchPP() {
        return matchPP;
    }

    public void setMatchPP(String matchPP) {
        this.matchPP = matchPP;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchMap() {
        return matchMap;
    }

    public void setMatchMap(String matchMap) {
        this.matchMap = matchMap;
    }

    public String getMatchPhotoImg() {
        return matchPhotoImg;
    }

    public void setMatchPhotoImg(String matchPhotoImg) {
        this.matchPhotoImg = matchPhotoImg;
    }

    @Override
    public String toString() {
        return "Matches [matchId=" + matchId + ", matchFees=" + matchFees + ", matchPrize=" + matchPrize
                + ", matchName=" + matchName + ", squadCriteria=" + squadCriteria + ", matchPP=" + matchPP
                + ", matchDate=" + matchDate + ", matchMap=" + matchMap + ", matchPhotoImg=" + matchPhotoImg + "]";
    }

}
