package com.dhf.controller;

import com.dhf.aop.AnnotationCustom;
import com.dhf.model.ObSystemAccount;
import com.dhf.model.Pager;
import com.dhf.model.User;
import com.dhf.service.ObSystemAccountService;
import com.dhf.service.RedisService;
import com.dhf.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	private Log log =  LogFactoryImpl.getLog(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/*@Autowired
	private HttpServletRequest request;*/
	
	/**
	 * 查询
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	//@RequiresRoles("denghf")
	@RequestMapping("/findAllUser")
	public String findAllUser(ModelMap m,HttpServletRequest request) throws Exception{
		//AnnotationParse.parse(UserController.class, "findAllUser");
		List<User> listUser = userService.findAllUser();
		List<User> list = new ArrayList<User>();
		if(listUser!=null && listUser.size()>0){
			for(int i=0;i< (listUser.size()>5?5:listUser.size());i++){
				list.add(listUser.get(i));
			}
			request.setAttribute("listUser", list);
			Pager p = new Pager();
			p.setAllSize(listUser.size());
			m.addAttribute("pager", p);
			log.info("查询完成");
		}
		return "/allUser";
	}
	
	/**
	 * 查看
	 * @param model
	 * @return
	 */
	@AnnotationCustom("aa")
	@RequestMapping("/detail")
	public String selectId(ModelMap model,HttpServletRequest request){
		String id = request.getParameter("id");
		User u = userService.selectId(Integer.valueOf(id));
		model.addAttribute("u", u);
		return "/detail";
	}
	
	/**
	 * 删除
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/delete/{id}")
	public String deleteId(@PathVariable int id,ModelMap model,HttpServletRequest request) throws Exception{
		//String id = request.getParameter("id");
		userService.deleteId(id);
		findAllUser(model,request);
		return "/allUser";
	}
	
	/**
	 * 修改跳转
	 * @return
	 */
	@RequestMapping("/updateJump/{id}")
	public String updateJump(@PathVariable int id,ModelMap model){
		User u = userService.selectId(Integer.valueOf(id));
		model.addAttribute("u", u);
		return "/updateJump";
	}
	
	/**
	 * 修改
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/update/{id}")
	public String update(ModelMap model,User user,HttpServletRequest request) throws Exception{
		userService.update(user);
		findAllUser(model,request);
		return "/allUser"; 
	}
	
	@RequestMapping(value="/pager")
	public String pager(ModelMap m,HttpServletRequest request){
		String pageSize  = request.getParameter("pageSize");
		List<User> listUser = userService.findAllUser();
		Pager p = new Pager();
		p.setPageSize(Integer.valueOf(pageSize)*5);
		p.setAllSize(listUser.size());
		p.setStartSize(Integer.valueOf(pageSize));
		List<User> list = null;
		if(pageSize!=null){
			list = userService.pager(p.getPageSize());
		}else{
			list = userService.pager(0);
		}
		m.addAttribute("pager", p);
		m.addAttribute("listUser", list);
		return "/allUser";
	}
}

