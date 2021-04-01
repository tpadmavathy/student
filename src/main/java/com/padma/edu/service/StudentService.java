package com.padma.edu.service;

import com.padma.edu.exception.EmptyRepositoryException;
import com.padma.edu.exception.StudentNotFoundException;
import com.padma.edu.model.Student;
import com.padma.edu.student.StudentRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private static final Logger LOGGER = LogManager.getLogger(StudentService.class.getName());

    public List<Student> getAllStudents() throws EmptyRepositoryException {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        if (students.size() == 0) {
            LOGGER.error("Data not available");
            throw new EmptyRepositoryException("Data not available");
        }
        return students;
    }

    public Optional<Student> getStudent(Integer rollNo) throws StudentNotFoundException {
        Optional<Student> student;
        student = studentRepository.findById(rollNo);
        if (student.isEmpty()) {
            LOGGER.error("Student data not found");
            throw new StudentNotFoundException("Student data not found");
        }
        return studentRepository.findById(rollNo);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Student student, Integer rollNo) throws StudentNotFoundException {
        Optional<Student> stud = studentRepository.findById(rollNo);
        if (stud.isEmpty()) {
            LOGGER.error("Can't update : Student data not found");
            throw new StudentNotFoundException("Can't update : Student data not found");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Integer rollNo) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(rollNo);
        if (student.isEmpty()) {
            LOGGER.error("Can't delete : Student data not found");
            throw new StudentNotFoundException("Can't delete : Student data not found");
        }
        studentRepository.deleteById(rollNo);
    }
}
