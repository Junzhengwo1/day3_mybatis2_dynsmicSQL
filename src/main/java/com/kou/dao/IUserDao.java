package com.kou.dao;

import com.kou.domain.QueryVo;
import com.kou.domain.User;
import org.apache.ibatis.annotations.Select;

import javax.management.Query;
import java.util.List;

/**
 * @author dell
 */
public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    //@Select("select * from user")
    List<User> findAll();


    /**
     * 根据Id查询用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据姓名模糊查询用户
     * @param name
     * @return
     */
    List<User> findByUsername(String name);


    /**
     * 根据查询条件查询用户， 综合条件查询
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询
     * @param user 可能有姓名 ，地址  等 或没有条件。
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据 id集合查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds( QueryVo vo);


}
