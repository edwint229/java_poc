package org.edwin.vote.mvc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.edwin.vote.mvc.to.JsonResult;
import org.edwin.vote.mvc.to.OptionTO;
import org.edwin.vote.mvc.to.VoteResult;
import org.edwin.vote.mvc.to.VoteTO;
import org.edwin.vote.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VoteController {

	private static Logger logger = LoggerFactory.getLogger(VoteController.class);

	private VoteService voteService;

	public VoteService getVoteService() {
		return voteService;
	}

	@Autowired
	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

	@RequestMapping(value = "/getVoteResult.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getVoteResult() {
		logger.info("getVoteResult GET called");
		try {
			VoteResult voteResult = voteService.getVoteResult(1);
			return JsonResult.success(voteResult);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.fail("系统错误");
		}
	}

	@RequestMapping(value = "/getVoteOptions.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getVoteOptions(HttpServletRequest request) {
		logger.info("getVoteOptions GET called");
		try {
			int selectId = 1;
			List<OptionTO> voteOptions = voteService.getVoteOptions(selectId);

			User userData = (User) request.getSession().getAttribute(LoginController.SESSION_ID_USERINFO);
			String userId = userData.getUsername();
			List<VoteTO> personalVoteResult = voteService.getPersonalVoteResult(userId, selectId);

			boolean isVoted = !CollectionUtils.isEmpty(personalVoteResult);
			if (isVoted) {
				for (OptionTO option : voteOptions) {
					for (VoteTO vote : personalVoteResult) {
						if (vote.getOptionId().equals(option.getId())) {
							option.setVoted(true);
						}
					}
				}
			}

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("voteOptions", voteOptions);
			result.put("isVoted", isVoted);
			return JsonResult.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.fail("系统错误");
		}
	}

	@RequestMapping(value = "/vote.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult vote(int selectId, String optionIds, HttpServletRequest request) {
		logger.info("vote POST called");
		try {

			if (StringUtils.isBlank(optionIds)) {
				return JsonResult.fail("请选择投票项。");
			}

			User userData = (User) request.getSession().getAttribute(LoginController.SESSION_ID_USERINFO);
			String userId = userData.getUsername();

			boolean isVoted = voteService.isVoted(userId, selectId);
			if (isVoted) {
				return JsonResult.fail("你已经投过票了。");
			}

			String[] optionIdArr = optionIds.split(",");
			for (String optionId : optionIdArr) {
				VoteTO voteTO = new VoteTO();
				voteTO.setUserId(userId);
				voteTO.setSelectId(selectId);
				voteTO.setOptionId(Integer.parseInt(optionId.trim()));
				voteTO.setCreatedDt(new Date());
				voteService.vote(voteTO);
			}

			return JsonResult.success("投票成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.fail("系统错误");
		}
	}
}
