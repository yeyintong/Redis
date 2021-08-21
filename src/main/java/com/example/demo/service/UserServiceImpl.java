package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Cacheable(value = "selectAll", key = "'userList'")
    public List<User> selectAll() {
        List<User> userList = userMapper.selectList(null);

        return userList;
    }

    //@CachePut(value = "selectAll", key = "'userList'")
    public int save(User user) {
        int rows = userMapper.insert(user);

        // 删除缓存
        if (rows > 0) {
            Set keys = redisTemplate.keys("*");
            Long delete = redisTemplate.delete(keys);
            System.out.println("delete = " + delete);
        }

        return rows;
    }
}
