package com.drelephant.elephantadmin.business.basedata.controller.base;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ForwardView;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.UTF8JsonView;
import org.springframework.beans.factory.annotation.Autowired;

import com.drelephant.framework.core.auth.entity.User;
import com.drelephant.framework.core.auth.service.AuthService;

/**
 * 
 * @author com.drelephant
 *
 */
public class BaseController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	@Autowired
	AuthService authService;
	
	public Log logger = Logs.get();

	protected void addCookie(String name, String value, int age) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(age);
		response.addCookie(cookie);
	}

	public HttpServletRequest request() {
		return request;
	}

	public String base() {
		return request.getContextPath();
	}
	public User user(){
		return authService.user();
	}
	protected String getCookie(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Strings.equals(cookie.getName(), name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public String getNameSpace() {
		return null;
	}

	public String ip() {
		return Lang.getIP(request);
	}

	protected void putSession(String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	public View renderForward(String path, Object[] objs) {
		request.setAttribute("objs", objs);
		return new ForwardView(path);
	}

	public View renderJson(Object[] objs) {
		UTF8JsonView view = (UTF8JsonView) UTF8JsonView.NICE;
		view.setData(objs);
		return view;
	}

	public View renderJsp(String path, Object[] objs) {
		request.setAttribute("objs", objs);
		return new JspView(path);
	}

	public View renderRedirct(String path) {
		return new ServerRedirectView(path);
	}
}
