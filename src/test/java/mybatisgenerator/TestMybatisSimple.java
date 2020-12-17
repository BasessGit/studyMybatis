package mybatisgenerator;

import cn.wuaijing.bean.User;
import cn.wuaijing.bean.UserExample;
import cn.wuaijing.mapper.UserMapper;
import net.sf.ehcache.search.expression.Criteria;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMybatisSimple {

    @Resource
    private  UserMapper userMapper;

    @Test
    public  void testGetUserSimple() {
      List<User> users = userMapper.selectAll();
        for (User u : users
             ) {
            System.out.println(u);
        }
        /*UserExample userExample = new UserExample();
        //拼装条件
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameLike("%小%")
                .andSexLike("1");
        UserExample userExample1 = new UserExample();
        UserExample.Criteria criteria1 = userExample.createCriteria();
        criteria1.andAddressLike("%广州%");
        userExample.or(criteria1);

        List<User> users = userMapper.selectByExample(userExample);
        for (User u: users
             ) {
            System.out.println(u);
        }*/

    }
        @Test
        public  void testUpdate(){

        }
    }