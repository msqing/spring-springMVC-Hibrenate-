package com.techsun.tccms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.techsun.tccms.exception.BusinessException;
import com.techsun.tccms.exception.ParameterException;
import com.techsun.tccms.service.AdminService;

public @Data class BaseController {
	
	@Autowired
	private AdminService adminService;
    /**
     * 得到request
     * @return
     */
    public HttpServletRequest getRequest(){
    	return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    /**
     * 得到response
     * @return
     */
    public HttpServletResponse getResponse(){
    	return ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
    }
    
    /**
     * 得到session
     * @return
     */
    public HttpSession getSession(){
    	return getRequest().getSession();
    }
    
    /** 基于@ExceptionHandler异常处理 */  
    @ExceptionHandler  
    public String exp(HttpServletRequest request, Exception ex) {  
        request.setAttribute("ex", ex);
        // 根据不同错误转向不同页面  
        if(ex instanceof BusinessException) {  
            return "error/business_error";  
        }else if(ex instanceof ParameterException) {
            return "error/parameter_error";  
        } else {  
            return "error";  
        }  
    }  
}
