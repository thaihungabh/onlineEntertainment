package edu.poly.sites.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.favoritedao;
import edu.poly.model.Favorite;
import edu.poly.model.Userss;
import edu.poly.model.Video;

/**
 * Servlet implementation class LikeVideoServlet
 */
public class LikeVideoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!SessionUtils.isLogin(request)) {
			response.sendRedirect("LoginServlet");
			return;
		}
		String page = request.getParameter("page");
		String videoId = request.getParameter("videoId");
		
		if(videoId == null) {
			response.sendRedirect("/Homepage");
			return;
		}
		
		try {
			favoritedao dao = new favoritedao();
			Favorite favorite = new Favorite();
			Video video = new Video();
			video.setVideoId(videoId);
			favorite.setVideo(video);
			
			String username = SessionUtils.getLogineUsername(request);
			Userss user = new Userss();
			user.setUsername(username);
			favorite.setUserss(user);
			
			favorite.setLikedDate(new Date());
			
			dao.insert(favorite);
			
			request.setAttribute("message", "Video is added to List");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		
		if(page == null) {
			page = "/Homepage";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
