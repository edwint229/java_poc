package org.edwin.vote.service;

import org.edwin.vote.dao.OutingRegisterDAO;
import org.edwin.vote.mvc.to.OutingRegisterTO;

public class OutingRegisterServiceImpl implements OutingRegisterService {
	private OutingRegisterDAO outingRegisterDAO;

	public OutingRegisterDAO getOutingRegisterDAO() {
		return outingRegisterDAO;
	}

	public void setOutingRegisterDAO(OutingRegisterDAO outingRegisterDAO) {
		this.outingRegisterDAO = outingRegisterDAO;
	}

	public void add(OutingRegisterTO outingRegister) throws Exception {
		outingRegisterDAO.add(outingRegister);
	}

	public boolean isRegisted(String userId) throws Exception {
		return 1 == outingRegisterDAO.getCount(userId);
	}

	public OutingRegisterTO getPersonalOutingRegister(String userId) {
		return outingRegisterDAO.getPersonalOutingRegister(userId);
	}

	public void update(OutingRegisterTO outingRegister) {
		outingRegisterDAO.update(outingRegister);
	}

}
