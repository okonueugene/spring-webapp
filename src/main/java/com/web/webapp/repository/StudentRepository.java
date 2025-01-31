package com.web.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.webapp.entity.Students;

public interface StudentRepository extends JpaRepository<Students, Integer> {
public Students findByRollNo(Integer rollNo);
}
