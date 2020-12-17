package mybatis;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/*

public class TestUserAndDept {
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;

    @Test
    public  void testSelectUserAndDept(){
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserAndDeptMapper userAndDeptMapper = sqlSession.getMapper(UserAndDeptMapper.class);
            User user = userAndDeptMapper.getUserAndDeptById(31);
            System.out.println(user);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }


    }


    */
/*分步查询*//*

    @Test
    public void testSelectUserAndDeptStep() {
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserAndDeptMapper userAndDeptMapper = sqlSession.getMapper(UserAndDeptMapper.class);
            User user = userAndDeptMapper.getUserAndDeptByIdStep(31);
            */
/*user.getUserName();*//*

            System.out.println(user.getUserName());
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }



    @Test
    public void testGetDeptAndUserList(){
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory =   new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
            Dept dept = deptMapper.getDeptByIdAndGetUserList(1);
            System.out.println(dept);
           System.out.println(dept.getList());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        }


    @Test
    public  void testGetDeptAndUserListStep(){
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();

           DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
            Dept dept = deptMapper.getDeptByIdAndGetUserListStep(2);
            System.out.println(dept.getId());
            System.out.println(dept.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }

    }

    @Test
    public void testGetUserAndDeptByIdStepDis(){
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession =    sqlSessionFactory.openSession();
            UserAndDeptMapper userAndDeptMapper = sqlSession.getMapper(UserAndDeptMapper.class);
            User user = userAndDeptMapper.getUserAndDeptByIdStepAndDis(26);
            System.out.println(user);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession !=null){
                sqlSession.close();
            }
        }

    }


    @Test
    public  void testGetUserByUserList(){
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            //user.setId(1);
            user.setUserName("%小%");
            List<User> users = userMapper.getUserByUserList(user);
            for (User u :  users
            ) {
                System.out.println(u);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public  void testGetUserByObjectAndChoose(){
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            //user.setUserName("%小%");
            user.setId(16);
           List<User> users =  userMapper.getUserByUserListAndChoose(user);
            for (User u : users
                 ) {
                System.out.println(u);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void updateUserByIdAndIf(){
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
         */
/*   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String s  = simpleDateFormat.format(new Date());*//*

            Long l = userMapper.updateUserByIdAndIf(new User(10,"小静",new Date(),"1","广东江门",null));
            System.out.println(l);
            if(l > 0){
                sqlSession.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           if(sqlSession != null){
               sqlSession.close();

           }
        }

    }

    @Test
    public void testGetUserByIds(){
        try {
            inputStream =  Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = userMapper.getUserByIdAndForeach(Arrays.asList(1,10,16,22));
            for (User user: users
                 ) {
                System.out.println(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }

    }


    @Test
    public void testAddUsers(){
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users  = new ArrayList<>();
            users.add(new User("静静",new Date(),"2","上海浦东",new Dept(1)));
            users.add(new User("阿静",new Date(),"2","广东广州", new Dept(2)));
            users.add(new User("小可爱",new Date(),"2","广东广州", new Dept(2)));
          Long l =   userMapper.inSetUsers(users);
            System.out.println(l);
          if(l> 0){
              sqlSession.commit();
          }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }

    }

    @Test
    public void testUsersByLiseUserName(){
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper  = sqlSession.getMapper(UserMapper.class);
            List<User> users = userMapper.getUserByLike("小");
            for (User u : users
            ) {
                System.out.println(u);
            }
            sqlSession.close();
            System.out.println("-------------------------------------------------------");
            SqlSession sqlSession1 = sqlSessionFactory.openSession();
           UserMapper userMapper1 =  sqlSession1.getMapper(UserMapper.class);
            List<User> users1 = userMapper1.getUserByLike("小");
            for (User u : users1
            ) {
                System.out.println(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }


    }

}
*/
