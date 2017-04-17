package com.techsun.tccms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		HttpSession session = req.getSession();
		if(session.getAttribute("userName") == null && !(req.getRequestURI().contains("loginPre") || req.getRequestURI().contains("login"))){
			res.sendRedirect("loginPre.do");
			return false;
		}
		return true;
	}

}
