package com.dhf.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import com.dhf.aop.AnnotationCustom;
import com.dhf.model.ObSystemAccount;
import com.dhf.model.TestGenerator;
import com.dhf.model.User;
import com.dhf.projectUtil.JsonResult;
import com.dhf.quartz.Job;
import com.dhf.quartz.SchedulerJob;
import com.dhf.service.ObSystemAccountService;
import com.dhf.service.RabbitMQService;
import com.dhf.service.RedisService;
import com.dhf.service.TestGeneratorService;
import com.dhf.service.UserService;

@Controller
@RequestMapping("/shiro")
public class LoginController {
	private Log log =  LogFactoryImpl.getLog(LoginController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	public RabbitMQService rabbitMQService;
	
	@Autowired
	private ObSystemAccountService obSystemAccountService;
	@Autowired
	private RedisService redisService;
	
	@Autowired CookieLocaleResolver resolver;
	
	@Resource
	private TestGeneratorService testGeneratorService;
	
	/**
	 * 到登录页面
	 * @return
	 */
	@AnnotationCustom("yeah")
	@RequestMapping("/login_page")
	public String login_page(){
		rabbitMQService.sendQueue("", "", "aaaaa");
		
		//一分钟挖一个比特币
		/*SchedulerJob schedulerJob1 = new SchedulerJob();
		Job job1 = new Job();
		job1.setBeanClass("com.dhf.controller.LoginController");
		job1.setName("btc");
		job1.setNum("1");
		schedulerJob1.addJob("0 0/1 * * * ?",job1,"BTC1");
		
		//两分钟挖一个比特币
		SchedulerJob schedulerJob2 = new SchedulerJob();
		Job job2 = new Job();
		job2.setBeanClass("com.dhf.controller.LoginController");
		job2.setName("btc");
		job2.setNum("2");
		schedulerJob2.addJob("0 0/2 * * * ?",job2,"BTC2");*/
		
		return "/login";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username, 
			@RequestParam("password") String password){
		Subject currentUser = SecurityUtils.getSubject();
		//测试当前的用户是否已经被认证. 即是否已经登录.
		//isAuthenticated和isRememberMe()只能其一为true
		if (!currentUser.isAuthenticated()) {
        	// 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // rememberme
            token.setRememberMe(true);
            try {
            	System.out.println("1. " + token.hashCode());
            	// 执行登录. 
                currentUser.login(token);
                
                //UserBean user= (UserBean) currentUser.getPrincipal();//获取登录成功的用户对象(以前是直接去service里面查)
            } 
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类. 
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            	System.out.println("登录失败: " + ae.getMessage());
            }
        }
		
		return "redirect:/user/findAllUser";
	}
	
	@RequestMapping("/error")
	public String login_error(){
		return "/error";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping("/jump")
	public String jump(){
		return "/save";
	}
	
	/**
	 * 新增
	 * @param u
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/save")
	//public ModelAndView save(User u){
	public String save(User u,HttpServletRequest request) throws IOException{
		//使用shiro进行MD5盐值加密1024次
		String hashAlgorithmName = "MD5";
		Object credentials = u.getPassword();
		Object salt = ByteSource.Util.bytes(u.getName());;
		int hashIterations = 1024;
		
		String password = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
		u.setPassword(password);
		int flag1 = userService.save(u);
		//添加虚拟账户
		ObSystemAccount obsystemaccout = new ObSystemAccount();
		obsystemaccout.setTotalmoney(new BigDecimal(0));
		obsystemaccout.setInvestpersonname(u.getName());
		obsystemaccout.setVersion(0);
		obsystemaccout.setInvestpersonId(u.getId());
		int flag2 = obSystemAccountService.insert(obsystemaccout);
		if(flag1==1&&flag2==1){
			redisService.redis_sadd(u, obsystemaccout);
		}
		List<User> listUser = userService.findAllUser();
		request.setAttribute("listUser", listUser);
		//return new ModelAndView("redirect:/user/findAllUser");
		//return "redirect:/user/findAllUser";
		return "/login";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		
		return "redirect:/shiro/login_page";
	}
	
	/**
	 * 语言切换
	 */
	@RequestMapping("/language")
	public ModelAndView language(HttpServletRequest request,HttpServletResponse response,String language){
		Cookie[] cookies = request.getCookies();
		language=language.toLowerCase();
		if(language==null||language.equals("")){
			return new ModelAndView("redirect:/");
		}else{
			if(language.equals("zh_cn")){
				resolver.setLocale(request, response, Locale.CHINA );
			}else if(language.equals("en")){
				resolver.setLocale(request, response, Locale.ENGLISH );
			}else{
				resolver.setLocale(request, response, Locale.CHINA );
			}
		}
		
		return new ModelAndView("redirect:/user/findAllUser");
	}
	
	private static int k = 0;
	
	public void btc(String num){
		k++;
		log.info(num+"分钟定时器，矿车：已挖到"+k+"个比特币");
	}
	
	//*********************************************交易大厅************************************************
	
	/**
	 * 到交易大厅页面
	 * @return
	 */
	@RequestMapping("/market")
	public String market(){
		return "/market";
	}
	
	
	@RequestMapping("/manualAdd")
	public void manualAdd() throws InterruptedException{
		for(int i=0;i<100;i++){
			TestGenerator t = new TestGenerator();
			t.setTransactionMoney(new BigDecimal(new Random().nextDouble()*100).setScale(2, BigDecimal.ROUND_HALF_DOWN));
			t.setRecordType(1);
			t.setCreated(new Date());
			testGeneratorService.insertSelective(t);
			Thread.sleep(500);
		}
	}
	
	/**
	 * 推送数据
	 * @return
	 */
	@RequestMapping("/marketdata")
	@ResponseBody
	public JsonResult marketdata(){
		List<TestGenerator> list = testGeneratorService.selectAll();
		return new JsonResult().setSuccess(true).setObj(list);
	}
	
	public static void main(String[] args) {
		System.out.println(new BigDecimal(new Random().nextDouble()*100).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}
}
