package edu.poly.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the Favorites database table.
 * 
 */
@Entity
@Table(name="Favorites")
@NamedQuery(name="Favorite.findAll", query="SELECT f FROM Favorite f")
public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FavoriteId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int favoriteId;

	@Column(name="LikedDate")
	private Date likedDate;

	//bi-directional many-to-one association to Userss
	@ManyToOne
	@JoinColumn(name="Username")
	private Userss userss;

	//bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name="VideoId")
	private Video video;

	public Favorite() {
	}

	public int getFavoriteId() {
		return this.favoriteId;
	}

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Date getLikedDate() {
		return this.likedDate;
	}

	public void setLikedDate(Date likedDate) {
		this.likedDate = likedDate;
	}

	public Userss getUserss() {
		return this.userss;
	}

	public void setUserss(Userss userss) {
		this.userss = userss;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}