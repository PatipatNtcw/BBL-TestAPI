package com.bbl.testApi.service;

import com.bbl.testApi.exception.BadRequestException;
import com.bbl.testApi.model.UserModel;
import com.bbl.testApi.service.facade.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceImpl {

    @Resource(name = "users")
    private List<UserModel> users;

    @Override
    public List<UserModel> getUsers() {
        return users;
    }

    @Override
    public List<UserModel> getUsersById(Long id) {
        List<UserModel> res = users.stream().filter(user -> user.getId().equals(id)).collect(Collectors.toList());
        if (res.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        return res;
    }

    @Override
    public UserModel createUsers(UserModel user) {
        if (user == null
                || isBlank(user.getName())
                || isBlank(user.getUsername())
                || isBlank(user.getEmail())) {
            throw new BadRequestException("name, username, and email are required");
        }
        users.add(user);
        return user;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    @Override
    public String deleteUser(Long id) {
        users.stream().filter(user -> user.getId().equals(id)).findFirst().ifPresent(users::remove);
        return "User deleted successfully";
    }

    @Override
    public UserModel updateUsers(Long id , UserModel requestUser) {

        if(requestUser != null && requestUser.getName() != null && !requestUser.getName().isEmpty() && requestUser.getEmail() != null && !requestUser.getEmail().isEmpty()) {
            users.stream().filter(user -> user.getId().equals(id)).findFirst().ifPresent(user -> {
                user.setId(id);
                user.setName(requestUser.getName());
                user.setEmail(requestUser.getEmail());
                user.setPhone(user.getPhone());
                user.setWebsite(user.getWebsite());
            });
        }else{
            throw new BadRequestException("404 Not Found");
        }
        return requestUser;
    }

}
