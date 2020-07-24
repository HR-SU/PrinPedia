package com.prinpedia.backend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.prinpedia.backend.entity.User;
import com.prinpedia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "/allUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllUsers() {
        List<User> userList = userService.findAllUsers();
        JSONArray extraData = new JSONArray();
        for(User user: userList) {
            JSONObject userInfo = new JSONObject();
            userInfo.put("username", user.getUsername());
            userInfo.put("email", user.getEmail());
            extraData.add(userInfo);
        }
        JSONObject response = new JSONObject();
        response.put("status", 0);
        response.put("message", "success");
        response.put("extraData", extraData);
        return response.toJSONString();
    }
}