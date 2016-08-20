package com.dq.dao;

import com.dq.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by DQ on 2016/8/17.
 * 定义数据库的DAO层的用户表接口
 *
 * DAO层：数据库的持久工作，先设计DAO的接口，然后在Spring的配置文件中定义此接口的实现类，
 * 然后就可在模块中调用此接口来进行数据业务的处理，而不用关心此接口的具体实现类是哪个类，显得结构非常清晰，
 * DAO层的数据源配置，以及有关数据库连接的参数都在Spring的配置文件中进行配置。
 *
 * DAO层的操作 经过抽象后基本上都是通用的，因而我们在定义DAO层的时候可以将相关的方法定义完毕，
 * 这样的好处是在对Service进行扩展的时候不需要再对DAO层进行修改，提高了程序的可扩展性。
 *
 * 通过注解的方式配置,一定要注入@Mapper,映射接口
 */
@Mapper
public interface UserDAO {
    String TABLE_NAME = "user";
    String INSERT_FIELDS =" name, password, salt, head_url ";
    String SELECT_FIELDS = " id, name, password, salt, head_url ";

    @Insert({"insert into ", TABLE_NAME, "(",INSERT_FIELDS,") values (#{name}, #{password}, #{salt}, #{headUrl})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, "from",TABLE_NAME, "where id = #{id}"})
    User selectById(int id);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where name = #{name}" })
    User selectByName(String name);

    @Update({"update ", TABLE_NAME, "set password = #{password} where id = #{id}"})
    void updatePassword(User user);

    @Update({"update ", TABLE_NAME, "set name = #{name} where id = #{id}"})
    void updateName(User user);

    @Delete({"delete from ", TABLE_NAME, "where id = #{id}"})
    void deleteById(int id);

}
