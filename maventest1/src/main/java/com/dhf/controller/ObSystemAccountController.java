package com.dhf.controller;

import com.dhf.model.ObSystemAccount;
import com.dhf.model.User;
import com.dhf.service.ObSystemAccountService;
import com.dhf.service.RedisService;
import com.dhf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping("/obsystemaccount")
public class ObSystemAccountController {
	
	@Autowired(required=true)
	private ObSystemAccountService obSystemAccountService;
	@Autowired(required=true)
	private UserService userService;
	@Autowired
	private RedisService redisService;
	
	@Resource
	private PlatformTransactionManager transactionManager;
	
	/**
	 * 跳转充值页面
	 * @return
	 */
	@RequestMapping("/rechargeJump/{id}")
	public String rechargeJump(@PathVariable int id,ModelMap model){
		//读取redis,如果没有，读mysql,再存入redis
		ObSystemAccount obsystemaccount = null;
		ObSystemAccount ob = redisService.redis_getMoney();
		if(ob==null){
			User u = userService.selectId(Integer.valueOf(id));
			obsystemaccount = obSystemAccountService.selectInvestId(u.getId());
			redisService.redis_updatemoney(obsystemaccount);
		}else{
			obsystemaccount = ob;
		}
		if(obsystemaccount!=null){
			model.addAttribute("obsystemaccount", obsystemaccount);
		}
		return "/recharge";
	}
	
	/**
	 * 充值
	 * @return
	 */
	@RequestMapping(value="/updateMoney/{id}")
	public void updateMoney(@PathVariable Long id,HttpServletRequest request){
		//先写入mysql再写入redis
		TransactionStatus status = transaction();
		try {
			String money = request.getParameter("money");//
			ObSystemAccount obSystemaccount = obSystemAccountService.selectByPrimaryKey(id);
			obSystemaccount.setMoney(new BigDecimal(money));
			BigDecimal totalMoneyNew = redisService.redis_setNx(obSystemaccount);
			
			obSystemaccount.setTotalmoney(totalMoneyNew);
			obSystemAccountService.updateByPrimaryKeySelective(obSystemaccount);
			
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
	        e.printStackTrace();
		}
		//return "redirect:/user/findAllUser";
	}
	
	/**
	 * 代码在controller层添加事务
	 * @return
	 */
	public TransactionStatus transaction(){
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
	    defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
	    return status;
	}

	@RequestMapping(value="/setNxMoneyAandS")
	public void setNxMoneyAandS(){
		String money = "100";//request.getParameter("money");
		ObSystemAccount obSystemaccount = new ObSystemAccount();
		obSystemaccount.setInvestpersonname("denghf");
		obSystemaccount.setMoney(new BigDecimal(money));
		redisService.redis_setNx(obSystemaccount);
	}
}
