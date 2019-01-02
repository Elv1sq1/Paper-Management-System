package com.tsu.zqy.service.serviceImpl;

import com.tsu.zqy.dao.StudentDao;
import com.tsu.zqy.pojo.Student;
import com.tsu.zqy.pojo.Teacher;
import com.tsu.zqy.pojo.User;
import com.tsu.zqy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class studentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public Student login(User user) {
        return studentDao.login(user);
    }

    @Override
    public Teacher getTeacherBytid(String gettId) {
        return studentDao.getTeacherBytid(gettId);
    }

    @Override
    public int upDateStudent(Student student) {
        return studentDao.upDateStudent(student);
    }

    @Override
    public int deleteWork(Integer id) {
        return studentDao.deleteWord(id);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentDao.getStudentById(id);
    }
}
