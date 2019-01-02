package com.tsu.zqy.controller;

import com.tsu.zqy.pojo.Student;
import com.tsu.zqy.pojo.Teacher;
import com.tsu.zqy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/chat")
    public String chat(String id, Model md) {
        Student student = teacherService.getStudentById(id);
        md.addAttribute("student", student);
        return "chat";
    }

    @RequestMapping("/toChat")
    public String toChat(String sid, String chatWord, HttpSession session) {
        teacherService.addChat(chatWord, sid);
        return "redirect:/teacher";
    }

    @RequestMapping("/toAddStudent")
    public String toAddStudent(String id, Model md) {
        md.addAttribute("id", id);
        return "addStudent";
    }

    /**
     * 添加学生
     * @param student
     * @param session
     * @param md
     * @return
     */
    @RequestMapping("/addStudent")
    public String addStudent(Student student, HttpSession session, Model md) {
        try {
            teacherService.addStudent(student);
            String tid = student.getTid();
            Teacher teacher = teacherService.getTeacherById(tid);
            List<Student> students = teacherService.getStudents(tid);
            teacher.setStudents(students);
            session.setAttribute("user", teacher);


            return "redirect:/teacher";
        } catch (Exception e) {
            return "redirect:/teacher";
        }
    }

    @RequestMapping("/teacher")
    public String teacher(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("user");
        String id = teacher.getId();
        List<Student> students = teacherService.getStudents(id);
        teacher.setStudents(students);
        session.setAttribute("user", teacher);
        return "teacher";
    }

    /**
     * 下载学生上传的论文
     *      下载的文件的名字为: 学号+姓名
     * @param id
     * @param response
     * @param md
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/download/{id}")
    public String downLoad(@PathVariable String id, HttpServletResponse response, Model md, HttpServletRequest request) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String userAgent = request.getHeader("USER-AGENT");

        //先获得学生对象
        Student student = teacherService.getStudentById(id);
        //获得当前学生上传的论文的物理路径
        String workPath = student.getHomework();
        String studentWorkname = student.getWorkname();
        //获取文件类型
        String type = studentWorkname.substring(studentWorkname.lastIndexOf("."));


        //论文名 :  "学号 + 姓名 .doc"
        String workname = student.getId() + "号" + student.getName() + type;

        File file = new File(workPath);

        //String finalFileName = null;
        //if(StringUtils.contains(userAgent, "MSIE")||StringUtils.contains(userAgent, "Trident") || StringUtils.contains(userAgent,"Edge")){//IE 浏览器
        //    finalFileName = URLEncoder.encode(workname,"UTF8");
        //}else{//火狐，google等其他浏览器
        //    finalFileName = new String(workname.getBytes("UTF-8"), "ISO-8859-1");
        //}

        if (!file.exists()) {
            md.addAttribute("msg", "下载失败");
            return "redirect:/teacher";
        } else {
            md.addAttribute("msg", "下载成功");
            // 设置相应头，控制浏览器下载该文件，这里就是会出现当你点击下载后，出现的下载地址框
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode(workname, "utf-8"));

            //response.setHeader("Content-Disposition", "attachment; filename=/"+ finalFileName + "/");   // 解决Firefox下载英文+中文组合的文件名的问题

            try {
                //输入流,获得论文
                FileInputStream in = new FileInputStream(file);
                //输出流,用于输出论文
                ServletOutputStream out = response.getOutputStream();
                //创建缓冲区
                byte[] bytes = new byte[1024];
                int len = 0;
                //流中还有字节,就读取
                while ((len = in.read(bytes)) > 0){
                    //每次读取len.size的字节
                    out.write(bytes,0,len);
                }
                //关流
                out.flush();
                in.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/teacher";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        int i = teacherService.deleteStudent(id);
        return "redirect:/teacher";
    }


}
