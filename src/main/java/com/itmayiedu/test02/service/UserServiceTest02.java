package com.itmayiedu.test02.service;

import com.itmayiedu.test02.dao.UserMapperTest02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceTest02 {
    @Autowired
    private UserMapperTest02 userMapperTest02;

    @Transactional
    public String insertTest002(String name, Integer age) {
        userMapperTest02.insert(name, age);
        int i = 1/0;
        return "success";
    }
}
