package com.techsun.tccms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.techsun.tccms.service.LogService;
import com.techsun.tccms.utils.QingUtils;


public class LogInterceptor implements HandlerInterceptor {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private LogService logService;
	Logger log = Logger.getLogger(LogInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest req,HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,Object arg2) throws Exception {
		String remoteAddr = req.getRemoteAddr();
		String realIp = req.getHeader("X-Real-IP");
		if(StringUtils.isNotEmpty(realIp)){
			remoteAddr = realIp;
		}
		JSONObject more = QingUtils.requestToJSON(req);
		JSONObject ua = QingUtils.userAgentFromString(req.getHeader("User-Agent"));
		HttpSession session = req.getSession();
		String user = "anonymous";
		if (null != session.getAttribute("userName")) {
			 user = (String) session.getAttribute("userName");
		}
		logService.log(user, ua.getString("browser"), ua.getString("os"), remoteAddr, req.getServletPath(), more.toString());
		return true;
	}

}
