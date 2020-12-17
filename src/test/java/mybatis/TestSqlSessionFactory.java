package mybatis;

import cn.wuaijing.bean.User;
import cn.wuaijing.mapper.UserMapper;
import cn.wuaijing.service.UsersService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSqlSessionFactory {

    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory = null;
    SqlSession sqlSession = null;
    User user = new User();
    @Test
    public void testSqlSessionFactoryXML() {

        try {
                inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            //这一步主要操作就是根据mybatisConfig的配置文件将主要配置文件的配置信息与Mapper配置文件信息全部封装到Configuration对象中，
            //然后根据Configuration 一个mappedStatement属性代表一个一个增删改查标签的详细信息。*/
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //主要是通过Configuration对象创建了一个Executor对象，然后通过Interceptor拦截器对象重新封装了Executor对象，并将所有插件设置到Executor对象中。
            //最后将Executor对象和Configuration对象全部封装到DefaultSqlSession中并返回
            /*Executor对象封装了所有的增删改查方法以及所有的插件*/
            sqlSession = sqlSessionFactory.openSession();
            /*这路通过上面返回DefaultSqlSession对象调用getMapper方法，内部其实是调用的Configuration对象的getMapper方法
            而Configuration对象又是调用RegistryMapper对象的getMapper方法，取出MapperProxyFactory,
            MapperProxyFactory调用newInstance方法中的Proxy.newProxyInstance方法完成对象的初始化，然后依次返回
            MapperProxy他是一个动态代理类，实现了InvocationHandler方法*/
            /*
            RegistryMapper主要就做两件事：
                            1.在初始化的mybatisConfig的时候，将MapperProxyFactory封装完成，放入Map中
                            2.在调用getMapper方法的时候，将MapperProxyFactory从Map中取出。
             */
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
           /* 动态代理的invoke方法返回了mapperMethod的execute方法，该方法先判断增删改查类型，然后根据类型调用相关的查询方法
            然后调用defaultSqkSession的相关方法，方法内部是调用Executor.query方法，然后将参数方法封装，根据方法查询，查询前看一下
            是否有缓存，有缓存直接从缓存中拿出来，如果执行其他修改，删除，更新方法，将缓存清除，如果没有缓存，调用SimpleExecutor.queryFromDataBase
            查询，这个查询又是调用BaseExecutor.doQuery方法，这个方法中创建了StatementHandler，ResultHandler,ParameterHandler等对象，
           并调用Interceptor拦截器.plugAll方法进行封装，查询， 将查询结果再放入缓存，并返回，返回结果是通TypeHandler进行封装*/
            //查询缓存，先查二级缓存，再查一级缓存
            //StatementHandler：用来处理预编译（通过ParameterHandler设置），设置参数等相关工作
            //ResultHandler：处理返回结果（借用TypeHandler）
            //ParameterHandler：设置预编译参数，
            //TypeHandler:用来处理数据库与javaBean的对象关系映射
           /*User user1 =  userMapper.selectByPrimaryKey(1);
            System.out.println(user1);*/
            Page<Object> page = PageHelper.startPage(1,5);
            List<User> users = userMapper.selectByExample(null);
            page.getPageNum();
            for (User u : users
            ) {
                System.out.println(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
             if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
        //System.out.println(user.getId()+","+user.getName()+","+user.getDate()+","+user.getSex()+","+user.getAddress());

    @Autowired
    UsersService usersService;
    @Test
    public void testAddUsers() throws IOException {

      /*  inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
       SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession(ExecutorType.BATCH);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addUsers(new User(null,"小花",new Date(),"1","安徽宣城",1));*/
       usersService.addUsers();

    }

    @Test
    public void testUsersAddIn() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
     SqlSessionFactory sqlSessionFactory =   new SqlSessionFactoryBuilder().build(inputStream);
     sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
     UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        for (int i=0;i<=10;i++){
            User user = new User(null, UUID.randomUUID().toString().substring(1,3),new Date(),"1","安徽宣城",1);
            userMapper.addUsers(user);
        }
    }
}

/*
    @Test
    public void testSqlSessionFactoryAnnotation(){

        InputStream inputStream;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        User user = new User();
        try {
            inputStream =   Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession =  sqlSessionFactory.openSession();
            // user  =  sqlSession.selectOne("cn.wuaijing.mapper.UserMapper.gerUserAll",10);
            UserMapperAnnotation userMapperAnnotation = sqlSession.getMapper(UserMapperAnnotation.class);
            user = userMapperAnnotation.getUserById(10);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession !=null){
                sqlSession.close();
            }
        }
        System.out.println(user);
    }

    @Test
    public void testAddUser(){

        InputStream inputStream;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            inputStream =   Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession =  sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
          User user =   new User("小微",new Date(),"1","上海浦东",new Dept(1));
            userMapper.insetUser(user);
            System.out.println(user.getId());
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession !=null){
                sqlSession.close();
            }
        }
    }

 */
/*   @Test
    public void testUpdateUser(){

        InputStream inputStream;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        User user = new User();
        try {
            inputStream =   Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession =  sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.updateUserById(new User(31,"小静",new Date(),"2","江门"));
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession !=null){
                sqlSession.close();
            }
        }
    }*//*

    @Test
    public void testDeleteUserById(){

        InputStream inputStream;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        User user = new User();
        try {
            inputStream =   Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession =  sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteUserById(37);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession !=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void testSelectUserByIdAndName(){
        InputStream inputStream;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        User user = new User();
        try {
            inputStream =   Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession =  sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
             //user = userMapper.getUserByIdAndName(1,"王五");

            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession !=null){
                sqlSession.close();
            }
        }
        System.out.println(user);
    }

    @Test
    public void testUserByMap(){
        InputStream inputStream;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        User user = new User();
        Map<String, Object> map = new HashMap<>();
        try {
            inputStream =   Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession =  sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
           // user = userMapper.getUserByIdAndName(1,"王五");
            map.put("id",1);
            map.put("sex", "2");
            user = userMapper.getUserByMap(map);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession !=null){
                sqlSession.close();
            }
        }
        System.out.println(user);
    }


    @Test
    public void testUserByResultMap(){
        InputStream inputStream;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        User user = new User();
        Map<String, Object> map = new HashMap<>();
        try {
            inputStream =   Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession =  sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // user = userMapper.getUserByIdAndName(1,"王五");
          user =  userMapper.getUserByIdToResultMap(28);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession !=null){
                sqlSession.close();
            }
        }
        System.out.println(user);
    }



}
*/
