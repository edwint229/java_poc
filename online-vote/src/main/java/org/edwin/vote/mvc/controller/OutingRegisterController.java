package org.edwin.vote.mvc.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.edwin.vote.mvc.to.JsonResult;
import org.edwin.vote.mvc.to.OutingRegisterTO;
import org.edwin.vote.service.OutingRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OutingRegisterController {

	private static Logger logger = LoggerFactory.getLogger(OutingRegisterController.class);

	private OutingRegisterService outingRegisterService;

	public OutingRegisterService getOutingRegisterService() {
		return outingRegisterService;
	}

	@Autowired
	public void setOutingRegisterService(OutingRegisterService outingRegisterService) {
		this.outingRegisterService = outingRegisterService;
	}

	@RequestMapping(value = "/outingRegister.do", method = RequestMethod.GET)
	public ModelAndView outingRegister() {
		return new ModelAndView("outingregister");
	}

	@RequestMapping(value = "/processOutingRegister.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult processOutingRegister(String name, String gender, String inditifyNo, String phoneNo, String remark, HttpServletRequest request) {
		logger.info("processOutingRegister called");
		try {
			if (StringUtils.isBlank(name)) {
				return JsonResult.fail("姓名不能为空");
			} else if (StringUtils.isBlank(gender)) {
				return JsonResult.fail("请选择性别");
			} else if (StringUtils.isBlank(inditifyNo)) {
				return JsonResult.fail("身份证不能为空");
			} else if (StringUtils.isBlank(phoneNo)) {
				return JsonResult.fail("手机号不能为空");
			}

			User userData = (User) request.getSession().getAttribute(LoginController.SESSION_ID_USERINFO);
			String userId = userData.getUsername();

			OutingRegisterTO outingRegister = new OutingRegisterTO();
			outingRegister.setUserId(userId);
			outingRegister.setName(name);
			outingRegister.setGender(gender);
			outingRegister.setInditifyNo(inditifyNo.toUpperCase());
			outingRegister.setPhoneNo(phoneNo);
			outingRegister.setRemark(remark);
			outingRegister.setCreatedDt(new Date());
			outingRegister.setUpdateDt(new Date());

			boolean isRegisted = outingRegisterService.isRegisted(userId);
			if (isRegisted) {
				outingRegisterService.update(outingRegister);
				return JsonResult.success("个人信息修改成功！");
			} else {
				outingRegisterService.add(outingRegister);
				return JsonResult.success("个人信息提交成功！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.fail("系统错误");
		}
	}

	@RequestMapping(value = "/getPersonalOutingRegister.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getPersonalOutingRegister(HttpServletRequest request) {
		logger.info("processOutingRegister called");
		try {
			User userData = (User) request.getSession().getAttribute(LoginController.SESSION_ID_USERINFO);
			String userId = userData.getUsername();
			OutingRegisterTO personalOutingRegister = outingRegisterService.getPersonalOutingRegister(userId);
			return JsonResult.success(personalOutingRegister);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.fail("系统错误");
		}
	}
}
