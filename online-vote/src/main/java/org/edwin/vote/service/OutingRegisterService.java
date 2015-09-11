package org.edwin.vote.service;

import org.edwin.vote.mvc.to.OutingRegisterTO;

public interface OutingRegisterService {

	public void add(OutingRegisterTO outingRegister) throws Exception;

	public boolean isRegisted(String userId) throws Exception;

	public OutingRegisterTO getPersonalOutingRegister(String userId);

	public void update(OutingRegisterTO outingRegister);

}
