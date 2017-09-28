package com.spg.apidoc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.alibaba.fastjson.JSON;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/")
public class IndexController {
	//基础缓存
	public static final String baseConfig="configCache:baseConfig";
	private final static Logger log = Logger.getLogger(IndexController.class);
	
	

	/**
	 * 主页
	 * 
	 * @return
	 */
	@RequestMapping(value="banner",produces = "application/json; charset=utf-8")
	@ApiOperation(value = "获取banner图", httpMethod = "POST", response = String.class, notes = "select banner")
	@ResponseBody
	public String banner() {
		
		return "";
	}

}
