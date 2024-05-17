//package com.example.demo.bean;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import lombok.Data;
//import org.apache.ibatis.type.EnumTypeHandler;
//
//import java.util.List;
//
//
//@TableName("user")
//@Data
//public class User {
//
//    @TableId(value = "id", type = IdType.AUTO)
//    private long id;
//
//    @TableField("name")
//    private String name;
//
//    @TableField(value = "sex", typeHandler = EnumTypeHandler.class)
//    private Sex sex;
//
//
////    @TableField(exist = false)
////    private Dept dept;
//
//    @TableField(exist = false)
//    private List<Dept> items;
//
//}
