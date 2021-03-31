package com.padma.edu.service;

import com.padma.edu.model.Student;
import com.padma.edu.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    /*private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student("Tom",21,101,'M',"Chennai",'C'),
            new Student("Jerry",20,102,'M',"Bangalore",'A'),
            new Student("Rapunzel",25,103,'F',"New Delhi",'B'),
            new Student("Elsa",22,104,'F',"Trivandrum",'A')
    ));*/

    public List<Student> getAllStudents()
    {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public Optional<Student> getStudent(Integer roll_no)
    {
        return studentRepository.findById(roll_no);
    }

    public void addStudent(Student student) {

        studentRepository.save(student);
    }

    public void updateStudent(Student student, Integer roll_no) {
        studentRepository.save(student);
    }

    public void deleteStudent(Integer roll_no) {
        studentRepository.deleteById(roll_no);
    }
}
