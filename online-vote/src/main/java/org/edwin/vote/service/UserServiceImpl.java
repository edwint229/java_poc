package org.edwin.vote.service;

import java.util.List;

import org.edwin.vote.dao.UserDAO;
import org.edwin.vote.mvc.to.UserTO;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void addUser(UserTO user) throws Exception {
        userDAO.create(user);
    }

    @Override
    public UserTO retrieveUserById(String userId) throws Exception {
        return userDAO.retrieveUserById(userId);
    }

    @Override
    public List<UserTO> retrieveAllUsers() throws Exception {
        return userDAO.retrieveAllUsers();
    }

    @Override
    public boolean isExists(String userId) throws Exception {
        return 1 == userDAO.getCount(userId);
    }

    @Override
    public void updatePassword(UserTO user) throws Exception {
        userDAO.updatePassword(user);
    }

}
