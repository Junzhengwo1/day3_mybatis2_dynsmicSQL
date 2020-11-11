package com.kou.test;

import com.kou.dao.IUserDao;
import com.kou.domain.QueryVo;
import com.kou.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.ibatis.io.Resources;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JIAJUN KOU
 * 测试类
 */

public class MybatisCRODTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    //执行初始化的操作
    @Before
    public void init() throws Exception{
        //1.读取配置文件，是为了加载信息。
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        //SqlSessionFactory factory=builder.build(in);
        //3.使用工厂生产SqlSession对象
        sqlSession=factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception{
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() throws Exception{

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindOne() throws Exception{

        User user = userDao.findById(41);
        System.out.println(user);
    }
    @Test
    public void testFindByUsername() {

        List<User> users = userDao.findByUsername("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByVO() {

        QueryVo vo=new QueryVo();
        User user=new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindByCondition(){
        User user=new User();
        user.setUsername("老王");
        user.setSex("女");
        List<User> users = userDao.findUserByCondition(user);
        for (User user1 : users) {
            System.out.println(user1);
        }

    }

    @Test
    public void testFindInIds(){
        QueryVo queryVo=new QueryVo();
        List<Integer> list=new ArrayList<Integer>();
        list.add(41);
        list.add(52);
        list.add(43);
        queryVo.setIds(list);
        List<User> users = userDao.findUserInIds(queryVo);
        for (User user : users) {
            System.out.println(user);
        }

    }
}
