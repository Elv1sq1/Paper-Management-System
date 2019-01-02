package com.tsu.zqy.service.serviceImpl;

import com.tsu.zqy.dao.TeacherDao;
import com.tsu.zqy.pojo.Student;
import com.tsu.zqy.pojo.Teacher;
import com.tsu.zqy.pojo.User;
import com.tsu.zqy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher login(User user) {
        return teacherDao.login(user);
    }

    @Override
    public List<Student> getStudents(String id) {
        return teacherDao.getStudents(id);
    }

    @Override
    public Student getStudentById(String id) {
        return teacherDao.getStudentById(id);
    }

    @Override
    public Teacher getTeacherById(String id) {
        return teacherDao.getTeacherById(id);
    }

    @Override
    public void addChat(String chatWord, String sid) {
        teacherDao.addChat(chatWord,sid);
    }

    @Override
    public int deleteStudent(String id) {
        return teacherDao.deleteStudent(id);
    }

    @Override
    public void addStudent(Student student) {
        teacherDao.addStudent(student);
    }
}
