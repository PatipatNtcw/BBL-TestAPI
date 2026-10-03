package com.bbl.testApi.service.facade;

import com.bbl.testApi.model.UserModel;

import java.util.List;

public interface UserServiceImpl {
    public List<UserModel> getUsers();
    public List<UserModel> getUsersById(Long id);
    public UserModel createUsers(UserModel user);
    public String deleteUser(Long id);
    public UserModel updateUsers(Long id ,UserModel user);
}
