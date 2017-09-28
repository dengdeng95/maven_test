package com.dhf.shiro;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import com.dhf.listener.StartupListener;
import com.dhf.model.ShiroAuthen;
import com.dhf.model.User;
import com.dhf.projectUtil.OrderedProperties;
import com.dhf.service.ShiroAuthenService;
import com.dhf.service.UserService;


public class FilterChainDefinitionMapBuilder{
	
	@Autowired
	private ShiroAuthenService shiroAuthenService;
	
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
		//没必要这么麻烦 存什么数据库啊 取什么取
		/*List<ShiroAuthen> list = shiroAuthenService.selectAll();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getKey()+" "+list.get(i).getValue());
			map.put(list.get(i).getKey(),list.get(i).getValue());
		}*/
		
		InputStream ins = FilterChainDefinitionMapBuilder.class.getClassLoader().getResourceAsStream("shiro/ShiroAuthen.properties");
		Properties pro=new OrderedProperties();
		try {
			pro.load(ins);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] key = pro.getProperty("shiro").split(",");
		for(int i=0;i<key.length;i++){
			String[] kk = key[i].split("=");
			System.out.println(kk[0]+"  "+kk[1]);
			map.put(kk[0],kk[1]);
		}
		
		
		
		
		/*map.put("/login.jsp", "anon");
		map.put("/user/findAllUser", "authc");
		map.put("/shiro/logout", "logout");*/
		
		
		/*map.put("/login.jsp", "anon");
		map.put("/user/jump", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");*/
		/*map.put("/user.jsp", "authc,roles[user]");
		map.put("/admin.jsp", "authc,roles[admin]");
		map.put("/list.jsp", "user");*/
		
		//map.put("/**", "authc");
		
		return map;
	}
}
