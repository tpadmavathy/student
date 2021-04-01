package com.padma.edu.student;

import com.padma.edu.exception.CustomException;
import com.padma.edu.model.Student;
import com.padma.edu.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value="students", description="Student Application Controller")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping("/students")
    @ApiOperation(value="Gets All Students")
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
    @ApiOperation(value="Gets Student with a specific Roll_No")
    public Optional<Student> getStudent(@PathVariable("roll") Integer roll_no)
    {
        return studentService.getStudent(roll_no);
    }

    @RequestMapping(method=RequestMethod.POST, value="/students")
    @ApiOperation(value="Adds New Student")
    public void addStudent(@RequestBody Student student)
    {
        studentService.addStudent(student);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/students/{roll}")
    @ApiOperation(value="Updates Existing Student")
    public void updateStudent(@RequestBody Student student, @PathVariable("roll") Integer roll_no)
    {
        studentService.updateStudent(student, roll_no);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/students/{roll}")
    @ApiOperation(value="Deletes a Student")
    public void deleteStudent(@PathVariable("roll") Integer roll_no)
    {
        studentService.deleteStudent(roll_no);
    }
}
