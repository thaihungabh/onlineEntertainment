package edu.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.poly.domain.FavoriteReport;
import edu.poly.domain.FavoriteUserReport;
import edu.poly.model.Favorite;

public class favoritedao extends AbstractEntityDao<Favorite> {

	public favoritedao() {
		super(Favorite.class);
		
	}
	
	public List<FavoriteUserReport> reportFavoriteUsersByVideo(String videoId){
		String jpql = "select new edu.poly.domain.FavoriteUserReport(f.userss.username, f.userss.fullname, "
				+ "f.userss.email, f.likedDate) from Favorite f where f.video.videoId = :videoId";
		
		EntityManager em = JpaUtils.getEntityManager();
		
		List<FavoriteUserReport> list = null;
		
		try {
			TypedQuery<FavoriteUserReport> query = em.createQuery(jpql, FavoriteUserReport.class);
			
			query.setParameter("videoId", videoId);
			
			list = query.getResultList();
		} finally{
			em.close();
		}
		return list;
	}
	
	public List<FavoriteReport> reportsFavoriteByVideos(){
		String jpql = "select new edu.poly.domain.FavoriteReport(f.video.title, count(f), min(f.likedDate), max(f.likedDate)) "
				+ " from Favorite f group by f.video.title ";
		
		EntityManager em = JpaUtils.getEntityManager();
		
		List<FavoriteReport> list = null;
		
		try {
			TypedQuery<FavoriteReport> query = em.createQuery(jpql, FavoriteReport.class);
			
			list = query.getResultList();
		} finally{
			em.close();
		}
		return list;
	}
	
}
