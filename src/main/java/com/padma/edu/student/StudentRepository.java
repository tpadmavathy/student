package com.padma.edu.student;

import com.padma.edu.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
}
