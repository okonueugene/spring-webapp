package com.web.webapp.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.web.webapp.entity.Students;
import com.web.webapp.repository.StudentRepository;

@Service
public class StudentsService {
    @Autowired
    private StudentRepository studentRepository;

    public Students addStudent(Students student) {
        return studentRepository.save(student);
    }
}
