package com.tsu.zqy.service;

import com.tsu.zqy.pojo.Student;
import com.tsu.zqy.pojo.Teacher;
import com.tsu.zqy.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TeacherService {

    Teacher login(User user);

    List<Student> getStudents(String id);

    Student getStudentById(String id);

    Teacher getTeacherById(String id);

    void addChat(String chatWord, String sid);

    void addStudent(Student student);

    int deleteStudent(String id);

}
