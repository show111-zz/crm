package com.itheima.crm.web.action;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 用户管理的Action的类
 * @author jt
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动使用的对象
	private User user = new User();
	public User getModel() {
		return user;
	}

	// 注入Service:
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户注册的方法:regist
	 */
	public String regist(){
		userService.regist(user);
		return LOGIN;
	}
	
	/**
	 * 用户登录的方法
	 * @return
	 */
	public String login() {
		User exitUser = userService.login(user);
		// 判断用户是否存在
		if(exitUser == null) {
			// 登录失败
			this.addActionError("用户名或密码错误！");
			return LOGIN;
		}else {
			// 登录成功
			// 将用户的信息存入到session
			ActionContext.getContext().getSession().put("existUser", exitUser);
			// 页面跳转
			return SUCCESS;
		}
	}
	
	
}
