package com.bbl.testApi.controller;

import ch.qos.logback.classic.Logger;
import com.bbl.testApi.model.ResponseBO;
import com.bbl.testApi.model.UserModel;
import com.bbl.testApi.service.facade.UserServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bblApi")
public class TestApiController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(TestApiController.class);

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBO getUsers() {
        return ResponseBO.builder().message(userService.getUsers()).status("200").build();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBO getUsersById(@RequestParam Long id) {
        return ResponseBO.builder().message(userService.getUsersById(id)).status("200").build();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBO createUser(@RequestBody UserModel req) {
        ResponseBO.builder().message("Created").status("201 ").build();
        userService.createUsers(req);
        return ResponseBO.builder().message("Created").status("201 ").build();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBO updateUsers(@RequestParam Long id, @RequestBody UserModel user) {
        return ResponseBO.builder().message(userService.updateUsers(id , user)).status("200").build();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBO deleteUser(@RequestParam Long id) {
        return ResponseBO.builder().message(userService.deleteUser(id)).status("200").build();
    }

}
