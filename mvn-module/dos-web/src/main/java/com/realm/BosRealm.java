package com.realm;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.damain.system.Resourcee;
import com.damain.system.User;
import com.service.system.ResourceeService;
import com.service.system.UserService;

public class BosRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;
	
	@Resource
	private ResourceeService resourceeService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("授权方法");
		//获得授权对象
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		Subject subject=SecurityUtils.getSubject();
		User loginUser=(User) subject.getPrincipal();
		
		List<Map<String, Object>> list=resourceeService.getMenuByUserId(loginUser.getId());
		for (Map<String, Object> map : list) {
			info.addStringPermission(map.get("grantKey").toString());
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken=(UsernamePasswordToken) token;
		//根据用户查询用户
		User loginUser=userService.getUserByName(userToken.getUsername());
		if (loginUser==null) {
			return null;
		}
		//自动验证密码
		return new SimpleAuthenticationInfo(loginUser,loginUser.getPassword(),"");
	}

}
