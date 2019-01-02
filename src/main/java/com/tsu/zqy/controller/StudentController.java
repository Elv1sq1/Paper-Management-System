package com.tsu.zqy.controller;

import com.tsu.zqy.pojo.Student;
import com.tsu.zqy.pojo.Teacher;
import com.tsu.zqy.service.StudentService;
import com.tsu.zqy.utils.UpLoadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @Value("${filePath}")
    String filePath;                        //文件保存路径

    //upload

    /**
     * 学生论文上传功能
     * 上传路径在配置文件中配置
     * 上传的文件后缀必须是.doc
     * @param file
     * @param session
     * @param md
     * @return
     */
    @PostMapping("upload")
    public String uploadImg(MultipartFile file, HttpSession session, Model md) {
        String contentType = file.getContentType();     //文件类型
        Student student = null;

        try{
            student = (Student) session.getAttribute("user");
            //判断文件上传类型是不是doc格式,如果不是则上传失败
            if(!contentType.equals("application/msword")){
                md.addAttribute("msg", "上传失败,上传文件类型不正确");
                Teacher teacher = studentService.getTeacherBytid(student.getTid());
                md.addAttribute("teacher", teacher);
                session.setAttribute("user", student);
                return "student";
            }
            String fileName = student.getId() +"号" + student.getName() + ".doc";
            String fileAddr = filePath + fileName;

            //文件上传
            UpLoadUtil.uploadFile(file.getBytes(), filePath, fileName);
            student.setHomework(fileAddr);
            student.setWorkname(fileName);
            int i = studentService.upDateStudent(student);
            if (i==1){
                md.addAttribute("msg", "上传成功");
            }
            else {
                md.addAttribute("msg", "上传失败");
            }
        }catch(Exception e) {
            md.addAttribute("msg", "上传失败");
            Teacher teacher = studentService.getTeacherBytid(student.getTid());
            md.addAttribute("teacher", teacher);
            session.setAttribute("user", student);
            return "student";
        }

        Teacher teacher = studentService.getTeacherBytid(student.getTid());
        md.addAttribute("teacher", teacher);
        session.setAttribute("user", student);
        return "student";
    }


    /**
     * 删除学生上传的论文,是逻辑删除,不是物理删除
     * 原始文件任然保存在upload文件夹中
     * @param id 学生id
     * @param md
     * @param session
     * @return
     */
    @GetMapping("toDelete/{id}")
    public String delete(@PathVariable Integer id, Model md, HttpSession session){
        int i = studentService.deleteWork(id);

        if (i==1){
            Student student = studentService.getStudentById(id);
            Teacher teacher = studentService.getTeacherBytid(student.getTid());
            md.addAttribute("teacher", teacher);
            session.setAttribute("user", student);
            md.addAttribute("msg", "删除成功");
            return "student";
        }
        md.addAttribute("msg", "删除失败");
        return "student";
    }

}
