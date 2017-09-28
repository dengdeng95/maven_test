package com.dhf.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.dhf.model.User;
import com.dhf.service.UserService;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;

	/**
	 * 用于授权的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		//arg0.getPrimaryPrincipal(): 实际上是在认证时返回的 SimpleAuthenticationInfo 的第一个参数!
		Object principal = arg0.getPrimaryPrincipal();
		Set<String> roles = new HashSet<>();
		roles.add(principal.toString());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}

	/**
	 * 用于认证的方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2. 从 UsernamePasswordToken 中来获取 username
		String username = upToken.getUsername();
		String password = new String(upToken.getPassword());
		
		//3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
		String hashAlgorithmName = "MD5";
		Object credentials = password;
		Object salt = ByteSource.Util.bytes(username);;
		int hashIterations = 1024;
		
		password = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
		User u = userService.select_login(username, password);
		
		//4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if(u==null){
			throw new UnknownAccountException("用户不存在");
			//5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常. 
		}else{
			//6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
			//以下信息是从数据库中获取的.
			//1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象. 
			Object principal = u.getName();
			//2). credentials: 密码. 
			//3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
			String realmName = getName();
			//4). 盐值. 
			ByteSource credentialsSalt = ByteSource.Util.bytes(principal);

			SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
			info = new SimpleAuthenticationInfo(principal, password, credentialsSalt, realmName);
			System.out.println("info=="+info);
			return info;
		}
		
	}

}
