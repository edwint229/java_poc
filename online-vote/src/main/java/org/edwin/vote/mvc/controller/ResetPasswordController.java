package org.edwin.vote.mvc.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.edwin.vote.mvc.to.JsonResult;
import org.edwin.vote.mvc.to.UserTO;
import org.edwin.vote.service.UserService;
import org.edwin.vote.util.MailSender;
import org.edwin.vote.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResetPasswordController {

	private static Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/sendPassword.do", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult processRegister(@ModelAttribute("user") UserTO user) {
		logger.info("send password POST called");
		try {
			if (StringUtils.isEmpty(user.getUserId())) {
				return JsonResult.fail("邮箱不能为空");
			}

			if (user.getUserId().contains("@")) {
				return JsonResult.fail("邮箱不正确");
			}

			// send password to user
			String password = SecurityUtil.getRandomString(8);
			String bodyContent = String.format("您的密码是%s", password);
			String emailAddress = String.format("%s@qq.com", user.getUserId());
			logger.info("send email to user {} ...", emailAddress);
			MailSender.send(emailAddress, "ONLINE VOTE SYSTEM PASSWORD", bodyContent);
			logger.info("send email to user {} successfully", emailAddress);

			// save user to db
			user.setPassword(SecurityUtil.encrypt(password));
			user.setRole("normal");
			user.setCreatedDt(new Date());

			boolean isUserNotExist = !userService.isExists(user.getUserId());
			if (isUserNotExist) {
				logger.info("save user {}", user.getUserId());
				userService.addUser(user);
			} else {
				logger.info("reset password for user {}", user.getUserId());
				userService.updatePassword(user);
			}

			return JsonResult.success("发送密码成功，请查看您的邮箱。");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.fail("系统错误");
		}
	}
}
