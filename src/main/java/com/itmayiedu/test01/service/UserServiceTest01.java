package com.itmayiedu.test01.service;

import com.itmayiedu.test01.dao.UserMapperTest01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceTest01 {
    @Autowired
    private UserMapperTest01 userMapperTest01;

    @Transactional
    public String insertTest001(String name, Integer age) {
        userMapperTest01.insert(name,age);
        int i = 1/0;
        return "success";
    }
}
