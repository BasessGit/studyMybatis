package cn.wuaijing.service;

import cn.wuaijing.bean.User;
import cn.wuaijing.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class UsersService {

    @Resource
    private    UserMapper userMapper;

    @Autowired
    private SqlSessionTemplate sqlSession;



    public List<User> getUsers(){
        return userMapper.selectAll();
    }

    public void addUsers(){

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            for (int i=0;i<=10;i++){
                User user = new User(null, UUID.randomUUID().toString().substring(1,3),new Date(),"1","安徽宣城",1);
                userMapper.addUsers(user);
            }
    }
}

