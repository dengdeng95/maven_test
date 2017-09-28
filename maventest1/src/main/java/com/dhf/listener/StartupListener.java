package com.dhf.listener;

import com.dhf.model.ShiroAuthen;
import com.dhf.projectUtil.OrderedProperties;
import com.dhf.service.ShiroAuthenService;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;


//import com.dhf.listener.mq.QueueConsumer;

/**
 * 
 * @author denghf
 * web.xml的加载顺序是context-param -> listener -> filter -> servlet
 * IoC容器启动时不会实例化bean，只有当容器需要用到时才实例化它，故在监听器中并没有实例化service
 */
public class StartupListener extends ContextLoaderListener{
	
	public void contextInitialized(ServletContextEvent event){
		//RabbitMQ消费者在启动项目时就启动
		/*Receiver receiver = null;
		try {
			receiver = new Receiver("obsystemaccount");
			Thread t = new Thread(receiver);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		
		//把shiro配置文件加到数据库中  
		//不用存了  没必要 2017年7月15日10:47:19
		
		/*WebApplicationContext ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		final ShiroAuthenService shiroAuthenService = (ShiroAuthenService) ctx.getBean("shiroAuthenServiceImpl");
		InputStream ins = StartupListener.class.getClassLoader().getResourceAsStream("shiro/ShiroAuthen.properties");
		Properties pro=new OrderedProperties();
		try {
			pro.load(ins);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] key = pro.getProperty("shiro").split(",");
		Set<String> set = pro.stringPropertyNames();
		
		for(int i=0;i<key.length;i++){
			ShiroAuthen shiroauthen = new ShiroAuthen();
			String[] kk = key[i].split("=");
			shiroauthen.setKey(kk[0]);
			shiroauthen.setValue(kk[1]);
			//shiroAuthenService.insert(shiroauthen);
		}*/
/*		List<ShiroAuthen> list = shiroAuthenService.selectAll();
		
		
		for(int i=0;i<list.size();i++){
			if(key==null){
				shiroAuthenService.deleteBykey(list.get(i).getKey());
			}
		}
		
		for(Iterator<String> it = set.iterator();it.hasNext();){
			String key = (String) it.next();
			String value = (String) pro.get(key);
			
			ShiroAuthen asAnd = shiroAuthenService.selectIDAnd(key,value);
			if(asAnd!=null){
				
			}else{
				ShiroAuthen shiroauthen = new ShiroAuthen();
				shiroauthen.setKey(key);
				shiroauthen.setValue(value);
				shiroAuthenService.insert(shiroauthen);
			}
		}*/
	}
}
