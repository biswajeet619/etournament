package com.etournament.proj.model;

import java.net.URL;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Match {

	@Id
	@GeneratedValue
	private int matchId;
	private int matchFees;
	private int matchPrize;
	private String matchName;
	private String squadCriteria;
	private String matchPP;
	private Date matchDate;
	private String matchMap;
	private URL matchPhotoImg;
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public int getMatchFees() {
		return matchFees;
	}
	public void setMatchFees(int matchFees) {
		this.matchFees = matchFees;
	}
	public int getMatchPrize() {
		return matchPrize;
	}
	public void setMatchPrize(int matchPrize) {
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
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	public String getMatchMap() {
		return matchMap;
	}
	public void setMatchMap(String matchMap) {
		this.matchMap = matchMap;
	}
	public URL getMatchPhotoImg() {
		return matchPhotoImg;
	}
	public void setMatchPhotoImg(URL matchPhotoImg) {
		this.matchPhotoImg = matchPhotoImg;
	}
	@Override
	public String toString() {
		return "Matches [matchId=" + matchId + ", matchFees=" + matchFees + ", matchPrize=" + matchPrize
				+ ", matchName=" + matchName + ", squadCriteria=" + squadCriteria + ", matchPP=" + matchPP
				+ ", matchDate=" + matchDate + ", matchMap=" + matchMap + ", matchPhotoImg=" + matchPhotoImg + "]";
	}
	
	
	
	
	
	
	
	
}
