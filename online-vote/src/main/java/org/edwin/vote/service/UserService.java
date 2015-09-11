package org.edwin.vote.service;

import java.util.List;

import org.edwin.vote.mvc.to.UserTO;

public interface UserService {

    public void addUser(UserTO user) throws Exception;

    public UserTO retrieveUserById(String userId) throws Exception;

    public List<UserTO> retrieveAllUsers() throws Exception;

    public boolean isExists(String userId) throws Exception;

    public void updatePassword(UserTO user) throws Exception;

}
