package org.edwin.vote.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.edwin.vote.mvc.to.LoginFormTO;
import org.edwin.vote.mvc.to.UserTO;
import org.edwin.vote.service.UserService;
import org.edwin.vote.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes(LoginController.SESSION_ID_USERINFO)
public class LoginController {

	public static final String SESSION_ID_USERINFO = "userInfo";

	private static final String COOKIE_ID = "onlinevote-cookie";

	private static final int DEFAULT_COOKIE_MAX_AGE = 7 * 24 * 3600;

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String handleDefault() {
		return "redirect:login.do";
	}

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView handleIndex() {
		ModelAndView mv = new ModelAndView();
		RedirectView view = new RedirectView("login.do");
		mv.setView(view);
		return mv;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView handleLogin(@CookieValue(value = COOKIE_ID, defaultValue = "") String cookieVal, HttpServletRequest request, HttpServletResponse response) {
		try {
			Object obj = request.getSession().getAttribute(SESSION_ID_USERINFO);
			if (null == obj && StringUtils.isNotBlank(cookieVal)) {
				String[] cookieVals = SecurityUtil.decrypt(cookieVal).split(",");
				String userId = cookieVals[0];
				String password = cookieVals[1];
				logger.info("User [{}] try login by cookie info", userId);
				UserTO user = userService.retrieveUserById(userId);
				if (null == user) {
					clearCookie(response);
					return new ModelAndView("index", "loginForm", new LoginFormTO());
				} else if (!user.getPassword().equals(password)) {
					clearCookie(response);
					return new ModelAndView("index", "loginForm", new LoginFormTO());
				} else {
					setUserInfoToSession(request, user);
					return new ModelAndView("redirect:loggedIn.do");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ModelAndView("redirect:/error");
		}
		return new ModelAndView("index", "loginForm", new LoginFormTO());

	}

	@RequestMapping(value = "/processLogin.do")
	public String processLogin(@ModelAttribute("loginForm") LoginFormTO loginForm, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginFormTO loginFormReq = (LoginFormTO) request.getAttribute("loginFormReq");
			if (null != loginFormReq) {
				loginForm = loginFormReq;
			} else {
				loginForm.setPassword(SecurityUtil.encrypt(loginForm.getPassword().trim()));
			}

			if (StringUtils.isEmpty(loginForm.getUserId())) {
				model.addAttribute("message", "邮箱不能为空");
				return "index";
			}

			if (StringUtils.isEmpty(loginForm.getPassword())) {
				model.addAttribute("message", "密码不能为空");
				return "index";
			}

			logger.info("loginForm : {}", loginForm.toString());

			UserTO user = userService.retrieveUserById(loginForm.getUserId());
			if (null == user) {
				model.addAttribute("message", "用户名或密码不正确");
				return "index";
			} else if (!user.getPassword().equals(loginForm.getPassword())) {
				model.addAttribute("message", "用户名或密码不正确");
				return "index";
			} else {
				setUserInfoToSession(request, user);
				setCookies(loginForm, response, user);

				return "redirect:loggedIn.do";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "redirect:/error";
		}
	}

	private void setUserInfoToSession(HttpServletRequest request, UserTO user) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		request.getSession().setAttribute(SESSION_ID_USERINFO, new User(user.getUserId(), user.getPassword(), authorities));
	}

	private void setCookies(LoginFormTO loginForm, HttpServletResponse response, UserTO user) throws Exception {
		if (loginForm.isChecked()) {
			String cookieVal = SecurityUtil.encrypt(String.format("%s,%s", loginForm.getUserId(), loginForm.getPassword()));
			addCookie(response, COOKIE_ID, cookieVal, DEFAULT_COOKIE_MAX_AGE);
			logger.info("Set cookie for user [{}] successfully", user.getUserId());
		} else {
			clearCookie(response);
		}
	}

	private void clearCookie(HttpServletResponse response) {
		addCookie(response, COOKIE_ID, null, 0);
	}

	private void addCookie(HttpServletResponse response, String cookieId, String cookieValue, int maxAge) {
		Cookie cookie = new Cookie(cookieId, cookieValue);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	@RequestMapping(value = "/loggedIn.do", method = RequestMethod.GET)
	public ModelAndView loggedIn() {
		return new ModelAndView("dashboard");
		//return new ModelAndView("outingregister");
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		return new ModelAndView("index", "loginForm", new LoginFormTO());
	}

}
