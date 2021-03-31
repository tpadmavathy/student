package com.padma.edu.student;

import com.padma.edu.exception.CustomException;
import com.padma.edu.model.Student;
import com.padma.edu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping("/students")
    public List<Student> getAllStudents()
    {
        try {
            if (studentService.getAllStudents().isEmpty()) {
                throw new CustomException("Data not available");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return studentService.getAllStudents();
    }

    @RequestMapping("/students/{roll}")
    public Optional<Student> getStudent(@PathVariable("roll") Integer roll_no)
    {
        return studentService.getStudent(roll_no);
    }

    @RequestMapping(method=RequestMethod.POST, value="/students")
    public void addStudent(@RequestBody Student student)
    {
        studentService.addStudent(student);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/students/{roll}")
    public void updateStudent(@RequestBody Student student, @PathVariable("roll") Integer roll_no)
    {
        studentService.updateStudent(student, roll_no);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/students/{roll}")
    public void deleteStudent(@PathVariable("roll") Integer roll_no)
    {
        studentService.deleteStudent(roll_no);
    }
}
