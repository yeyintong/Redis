package com.example.demo.controller;

import com.example.demo.entity.R;
import com.example.demo.entity.User;
import com.example.demo.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "用户Controller")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "查询所有用户")
    @RequestMapping("/selectAll")
    public R selectAll() {
        List<User> userList = userService.selectAll();

        return R.ok().data("userList", userList);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    public R add(@RequestBody User user) {
        int i = userService.save(user);

        return R.ok().data("rows", i);
    }
}
