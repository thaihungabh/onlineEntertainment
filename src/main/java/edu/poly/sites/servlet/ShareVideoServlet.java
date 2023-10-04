package edu.poly.sites.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import edu.poly.common.EmailUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.Sharedao;
import edu.poly.domain.Email;
import edu.poly.model.Share;
import edu.poly.model.Userss;
import edu.poly.model.Video;

/**
 * Servlet implementation class ShareVideoServlet
 */
public class ShareVideoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!SessionUtils.isLogin(request)) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		String videoId = request.getParameter("videoId");
		
		if(videoId == null) {
			response.sendRedirect("/Homepage");
			return;
		}
		
		request.setAttribute("videoId", videoId);
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_SHARE_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String videoId = request.getParameter("videoId");
			
			if(videoId==null) {
				request.setAttribute("error", "VideoId is null");
			}else {
				Email email = new Email();
				email.setFrom("thaihungnguyenabh@gmail.com");
				email.setFromPassword("Hung@01091997");
				email.setTo(emailAddress);
				email.setSubject("Share Favorite video");
				StringBuilder sb = new StringBuilder();
				sb.append("Dear Ms/Mr. <br>");
				sb.append("The video is more interesting and I want to share with you. <br> ");
				sb.append("Please click the link").append(String.format("<a href=''>View Video</a><br>", 
						videoId));
				sb.append("Regard<br>");
				sb.append("Administrator");
				
				email.setContent(sb.toString());
				EmailUtils.send(email);
				
				Sharedao dao = new Sharedao();
				Share share = new Share();
				share.setEmails(emailAddress);
				share.setSharedDate(new Date());
				
				String username = SessionUtils.getLogineUsername(request);
				Userss user = new Userss();
				user.setUsername(username);
				
				share.setUserss(user);
				Video video = new Video();
				video.setVideoId(videoId);
				share.setVideo(video);
				
				dao.insert(share);
				request.setAttribute("message", "Video link has been sent");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_SHARE_PAGE);
	}

}
