package com.example.demo.dao;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.example.demo.bean.User;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.ResultMap;
//import org.apache.ibatis.annotations.Select;
//
//import java.util.List;
//
//@Mapper
//public interface UserMapper extends BaseMapper<User> {
//
////    @Select("SELECT u.*, d.* FROM user u LEFT JOIN dept d ON u.id = d.id WHERE u.id = #{id}")
////    @ResultMap("userResult")
////    User selectById(@Param("id") long id);
//
//
//    @Select("SELECT u.*, d.* FROM user u LEFT JOIN dept d ON u.id = d.id ")
//    @ResultMap("userOneToMany")
//    List<User> userOneToManySelect(@Param("id") long id);
//
//}
