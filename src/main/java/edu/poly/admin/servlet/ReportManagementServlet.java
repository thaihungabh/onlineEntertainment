package edu.poly.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.VideoDao;
import edu.poly.dao.favoritedao;
import edu.poly.domain.FavoriteReport;
import edu.poly.domain.FavoriteUserReport;
import edu.poly.model.Video;

/**
 * Servlet implementation class ReportManagementServlet
 */
public class ReportManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reportFavoriteByVideos(request, response);
		reportFavoriteUsersByVideos(request, response);
		
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void reportFavoriteUsersByVideos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String videoUserId = request.getParameter("videoUserId");
			
			VideoDao vdao = new VideoDao();
			List<Video> vList = vdao.findAll();
			
			if(videoUserId == null && vList.size()>0) {
				videoUserId = vList.get(0).getVideoId();
			}
			
			favoritedao dao = new favoritedao();
			List<FavoriteUserReport> list = dao.reportFavoriteUsersByVideo(videoUserId);
			
			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vidList", vList);
			request.setAttribute("favUsers", list);
			
		} catch (Exception e) {
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
	
	protected void reportFavoriteByVideos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			favoritedao dao = new favoritedao();
			List<FavoriteReport> list = dao.reportsFavoriteByVideos();
			
			request.setAttribute("favList", list);
			
		} catch (Exception e) {
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
}
