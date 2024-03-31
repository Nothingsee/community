package com.example.community.mapper;

import com.example.community.model.Article;
import com.example.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into sys_user(name,account_id,token,gmt_create,gmt_modified,avatar_url) " +
            "values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from sys_user where token = #{token}")
    User findUserByToken(@Param("token") String token);

    @Select("select * from sys_user where name = #{name}")
    User findUserByName(@Param("name") String name);

    @Select("select * from sys_user where id = #{userId}")
    User findUserByUserId(@Param("userId") Long userId);
}
