package com.techsun.tccms.web.controller;

import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techsun.tccms.entity.User;
import com.techsun.tccms.exception.BusinessException;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	
	private String nameSpace = "admin/";
	private String name = "张三";
	
	@RequestMapping("main")
	public String mainpage(ModelMap model){
		model.addAttribute("name", name);
		return nameSpace + "main";
	}
	
	@RequestMapping("addUser")
	public void addUser(User user){
		throw new BusinessException("业务异常");
//		super.getAdminService().addUser(user);
//		return "redirect:main.do";  
	}
	
	@RequestMapping("loginPre")
	public String loginPre(){
		return nameSpace + "loginPre";
	}
	
	@RequestMapping("login")
	public void login(User user,PrintWriter out){
		super.getSession().setAttribute("userName", user.getName());
		out.write("恭喜，"+user.getName()+"注册成功！");
	}
}
