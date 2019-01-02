package com.tsu.zqy.dao;

import com.tsu.zqy.pojo.Student;
import com.tsu.zqy.pojo.Teacher;
import com.tsu.zqy.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherDao {

    @Select("select * from teacher where username=#{username} and password=#{password}")
    Teacher login(User user);

    @Select("select * from student where tid=#{id}")
    List<Student> getStudents(String id);
    @Select("select * from student where id=#{id}")
    Student getStudentById(String id);

    @Update("update student set chat = #{chatWord} where id = #{sid}")
    void addChat(String chatWord, String sid);

    @Insert("insert into student(username,name,password,age,tid,cid) " +
            "values(#{username},#{name},#{password},#{age},#{tid},#{cid}) ")
    @Options(keyColumn = "id",keyProperty = "id",useGeneratedKeys = true)
    void addStudent(Student student);

    @Select("select * from teacher where id=#{id}")
    Teacher getTeacherById(String id);
    @Delete("delete from student where id=#{id}")
    int deleteStudent(String id);
}
