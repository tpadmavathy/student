package com.padma.edu.service;

import com.padma.edu.exception.EmptyRepositoryException;
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



    public List<Student> getAllStudents() throws EmptyRepositoryException {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        if (students.size() == 0) {
            throw new EmptyRepositoryException("Data not available");
        }
        return students;
    }

    public Optional<Student> getStudent(Integer rollNo) {
        return studentRepository.findById(rollNo);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Student student, Integer rollNo) {
        studentRepository.save(student);
    }

    public void deleteStudent(Integer rollNo) {
        studentRepository.deleteById(rollNo);
    }
}
