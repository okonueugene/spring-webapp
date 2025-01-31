package com.web.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.web.webapp.entity.Students;
import com.web.webapp.entity.StudentsDto;
import com.web.webapp.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentRepository studentRepository;


    @GetMapping({ "/", "" })
    public String getStudents(Model model) {
        var students = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("students", students);
        return "students/index";
    }

    @GetMapping("/create")
    public String createStudent(Model model) {
        StudentsDto studentsDto = new StudentsDto();
        model.addAttribute("studentsDto", studentsDto);
        return "students/create";
    }
    
    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute StudentsDto studentsDto, BindingResult result) {
        if(studentRepository.findByRollNo(studentsDto.getRollNo()) != null) {
            result.addError(
                new FieldError("studentsDto", "rollNo", studentsDto.getRollNo(), false, null ,null,"Roll No already exists")
            );
        }

        if(result.hasErrors()) {
            return "students/create";
        }
        
        Students student = new Students();
        student.setName(studentsDto.getName());
        student.setRollNo(studentsDto.getRollNo());
        student.setClassName(studentsDto.getClassName());
        student.setProfile(studentsDto.getProfile());
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/edit")
    public String editStudent(Model model, @RequestParam int id) {
        Students student = studentRepository.findById(id).orElse(null);

        if(student == null) {
            return "redirect:/students";
        }
    
        StudentsDto studentsDto = new StudentsDto();
        studentsDto.setName(student.getName());
        studentsDto.setRollNo(student.getRollNo());
        studentsDto.setClassName(student.getClassName());

        model.addAttribute("studentsDto", studentsDto);
        return "students/edit";
    }

    @PostMapping("/edit")
    public String editStudent(Model model, @RequestParam int id, @Valid @ModelAttribute StudentsDto studentsDto, BindingResult result) {

        Students student = studentRepository.findById(id).orElse(null);

        if(student == null) {
            return "redirect:/students";
        }

        model.addAttribute("student", student);

        if(result.hasErrors()) {
            return "students/edit";
        }

        student.setName(studentsDto.getName());
        student.setRollNo(studentsDto.getRollNo());
        student.setClassName(studentsDto.getClassName());
        student.setProfile(studentsDto.getProfile());

        try {
            studentRepository.save(student);
        } catch (Exception e) {
            result.addError(
                new FieldError("studentsDto", "rollNo", studentsDto.getRollNo(), false, null ,null,"Roll No already exists")
            );
            return "students/edit";
        }
        
        return "redirect:/students";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam int id) {
        Students student = studentRepository.findById(id).orElse(null);

        if(student == null) {
            return "redirect:/students";
        }

        studentRepository.delete(student);
        return "redirect:/students";
    }
}
