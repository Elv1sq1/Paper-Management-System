package com.tsu.zqy.service;

import com.tsu.zqy.pojo.Student;
import com.tsu.zqy.pojo.Teacher;
import com.tsu.zqy.pojo.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentService {



    //学生登录
    Student login(User user);

    Teacher getTeacherBytid(String gettId);

    //学生更新数据
    int upDateStudent(Student student);

    int deleteWork(Integer id);

    Student getStudentById(Integer id);
}