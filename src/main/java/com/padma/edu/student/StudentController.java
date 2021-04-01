package com.padma.edu.student;

import com.padma.edu.exception.EmptyRepositoryException;
import com.padma.edu.exception.StudentNotFoundException;
import com.padma.edu.model.Student;
import com.padma.edu.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "students", description = "Student Application Controller")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping(method = RequestMethod.GET, value = "/students", produces={"application/json"}, consumes = {"application/json"})
    @ApiOperation(value = "Gets All Students")
    public List<Student> getAllStudents() throws EmptyRepositoryException {
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/students/{roll}", produces={"application/json"}, consumes = {"application/json"})
    @ApiOperation(value = "Gets Student with a specific rollNo")
    public Optional<Student> getStudent(@PathVariable("roll") Integer rollNo) throws StudentNotFoundException {
        return studentService.getStudent(rollNo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addstudent", consumes = {"application/json"})
    @ApiOperation(value = "Adds New Student")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/students/{roll}/update", consumes = {"application/json"})
    @ApiOperation(value = "Updates Existing Student")
    public void updateStudent(@RequestBody Student student, @PathVariable("roll") Integer rollNo) throws StudentNotFoundException{
        studentService.updateStudent(student, rollNo);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{roll}/delete", consumes = {"application/json"})
    @ApiOperation(value = "Deletes a Student")
    public void deleteStudent(@PathVariable("roll") Integer rollNo) throws StudentNotFoundException {
        studentService.deleteStudent(rollNo);
    }
}
