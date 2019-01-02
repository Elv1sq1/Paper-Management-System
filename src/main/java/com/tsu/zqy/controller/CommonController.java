package com.tsu.zqy.controller;

import com.tsu.zqy.pojo.Student;
import com.tsu.zqy.pojo.Teacher;
import com.tsu.zqy.pojo.User;
import com.tsu.zqy.service.StudentService;
import com.tsu.zqy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/login")
    public String login(Model md) throws IOException {
        //CheckCodeUtil.makeImg();
        //String img = CheckCodeUtil.getName();
        //String word = CheckCodeUtil.getWord2();
        //
        //md.addAttribute("img",img);
        //md.addAttribute("word",word);

        return "login";
    }


    @RequestMapping("/index")
    public String index(User user, HttpSession session, Model md) {
        String job = user.getJob();
        if (job.equals("student")) {
            //Student student = studentService.getStudentByusername(user.getUsername());
            Student student = studentService.login(user);
            if (student != null) {
                Teacher teacher = studentService.getTeacherBytid(student.getTid());

                md.addAttribute("teacher", teacher);
                session.setAttribute("user", student);
                return "student";
            } else {
                return "redirect:/login.html";
            }
        } else {
            Teacher teacher = teacherService.login(user);
            List<Student> students = teacherService.getStudents(teacher.getId());
            teacher.setStudents(students);
            if (teacher != null) {
                session.setAttribute("user", teacher);
                return "teacher";
            } else {
                return "redirect:/login.html";
            }
        }
    }

    @RequestMapping("/")
    public String welcomePage() {
        return "redirect:/login.html";
    }

    //ajax
    @ResponseBody
    @RequestMapping("getValue")
    public List<Student> getStudents(String id) {
        List<Student> students = teacherService.getStudents(id);
        return students;
    }
}
