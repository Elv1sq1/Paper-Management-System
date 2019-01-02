package com.tsu.zqy.dao;

import com.tsu.zqy.pojo.Student;
import com.tsu.zqy.pojo.Teacher;
import com.tsu.zqy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentDao {


    @Select("select * from student where username=#{username} and password=#{password}")
    Student login(User user);

    @Select("select * from teacher where id=#{gettId}")
    Teacher getTeacherBytid(String gettId);

    @Update("update student set username=#{username}," +
            "password=#{password}," +
            "name=#{name}, " +
            "age=#{age}, " +
            "homework=#{homework}, " +
            "workname=#{workname} "+
            "where id=#{id}")
    int upDateStudent(Student student);

    @Update("update student set homework=''," +
             "workname='' "+
            "where id=#{id}")
    int deleteWord(Integer id);

    @Select("select * from student where id=#{id}")
    Student getStudentById(Integer id);
}

