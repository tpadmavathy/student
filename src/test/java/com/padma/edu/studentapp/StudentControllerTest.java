package com.padma.edu.studentapp;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.padma.edu.model.Student;
import com.padma.edu.service.StudentService;
import com.padma.edu.student.StudentController;
import com.padma.edu.student.StudentRepository;
import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.core.Is.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StudentController.class)

public class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean(name="studentRepository")
    private StudentRepository studentRepository;

    @Test
    public void getAllStudents() throws Exception{
        Student student = new Student("Tom", 25, 101, 'M', "Chennai", 'B');
        List<Student> studList = Collections.singletonList(student);

        given(studentController.getAllStudents()).willReturn(studList);
        mvc.perform(MockMvcRequestBuilders.get("/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudent() throws Exception{
        Student student = new Student("Jerry", 24, 102, 'M', "Chennai", 'A');
        given(studentController.getStudent(student.getRollNo())).willReturn(java.util.Optional.of(student));
        mvc.perform(MockMvcRequestBuilders.get("/students/"+student.getRollNo())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("roll_no", is(student.getRollNo())));
    }

    @Test
    public void addStudent() throws Exception{
        Student student = new Student("Tom", 26, 103, 'M', "Bangalore", 'A');

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        ResultActions resultActions = mvc.perform(post("/students")
                //.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(student))
                //.content("{json}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void updateStudent() throws Exception{

        Student student = new Student("Tom", 26, 103, 'M', "Bangalore", 'A');

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(student))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

        student.setRollNo(123);

        mvc.perform(MockMvcRequestBuilders.put("/students/"+student.getRollNo())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(student))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteStudent() throws Exception{
        Student student = new Student("Elsa", 26, 105, 'F', "Bangalore", 'A');
        //Mockito.when(StudentController.deleteStudent(student.getRoll_no())).thenReturn("SUCCESS");
        mvc.perform(MockMvcRequestBuilders.delete("/students/"+student.getRollNo()))
                .andExpect(status().isOk());
    }
}