package edu.poly.domain;

import java.util.Date;

public class FavoriteReport {
	private String videoTitle;
	private Long favoritesCount;
	private Date newesDate;
	private Date oldesDate;
	
	
	public FavoriteReport() {
	}
	public FavoriteReport(String videoTitle, Long favoritesCount, Date newesDate, Date oldesDate) {
		this.videoTitle = videoTitle;
		this.favoritesCount = favoritesCount;
		this.newesDate = newesDate;
		this.oldesDate = oldesDate;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public Long getFavoritesCount() {
		return favoritesCount;
	}
	public void setFavoritesCount(Long favoritesCount) {
		this.favoritesCount = favoritesCount;
	}
	public Date getNewesDate() {
		return newesDate;
	}
	public void setNewesDate(Date newesDate) {
		this.newesDate = newesDate;
	}
	public Date getOldesDate() {
		return oldesDate;
	}
	public void setOldesDate(Date oldesDate) {
		this.oldesDate = oldesDate;
	}
	
	
}
