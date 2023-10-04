package edu.poly.common;


import java.io.IOException;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageInfo {
	public static Map<PageType, PageInfo> pageRoute = new HashedMap();
	
	static {
		pageRoute.put(PageType.USER_MANAGEMENT_PAGE, new PageInfo("User Management", "/admin/users/users.jsp", null));
		pageRoute.put(PageType.REPORT_MANAGEMENT_PAGE, new PageInfo("Report", "/admin/reports/reports.jsp", null));
		pageRoute.put(PageType.VIDEO_MANAGEMENT_PAGE, new PageInfo("Videos Management", "/admin/videos/videos.jsp", null));
		
		pageRoute.put(PageType.SITE_HOME_PAGE, new PageInfo("Home", "/sites/home/home.jsp", null));
		pageRoute.put(PageType.SITE_LOGIN_PAGE, new PageInfo("Login", "/sites/users/login.jsp", null));
		pageRoute.put(PageType.SITE_REGISTER_PAGE, new PageInfo("Registration", "/sites/users/registration.jsp", null));
		pageRoute.put(PageType.SITE_CHANGEPASSWORD_PAGE, new PageInfo("Change-Password", "/sites/users/change-password.jsp", null));
		pageRoute.put(PageType.SITE_EDITPROFILE_PAGE, new PageInfo("Edit-Profile", "/sites/users/edit-profile.jsp", null));
		pageRoute.put(PageType.SITE_FORGOTPASSWORD_PAGE, new PageInfo("Forgot-Password", "/sites/users/forgot-password.jsp", null));
		pageRoute.put(PageType.SITE_SHARE_PAGE, new PageInfo("Shares", "/sites/videos/share.jsp", null));
		pageRoute.put(PageType.SITE_DETAIL_PAGE, new PageInfo("Detail", "/sites/videos/detail.jsp", null));
		pageRoute.put(PageType.SITE_FAVORITE_PAGE, new PageInfo("Favorites", "/sites/videos/favorite.jsp", null));
	}
	
	public static void prepareAndForward(HttpServletRequest request ,HttpServletResponse response ,PageType pageType) throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageType);
		
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/admin/layout.jsp").forward(request, response);
	}
	
	public static void prepareAndForwardSite(HttpServletRequest request ,HttpServletResponse response ,PageType pageType) throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageType);
		
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/sites/layout.jsp").forward(request, response);
	}
	
	private String title;
	private String contentUrl;
	private String scriptUrl;
	
	
	public PageInfo(String title, String contentUrl, String scriptUrl) {
		super();
		this.title = title;
		this.contentUrl = contentUrl;
		this.scriptUrl = scriptUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getScriptUrl() {
		return scriptUrl;
	}
	public void setScriptUrl(String scriptUrl) {
		this.scriptUrl = scriptUrl;
	}
	
	
}
