package com.geekster.ModelStudent.controller;

import com.geekster.ModelStudent.model.Student;
import com.geekster.ModelStudent.service.StudentService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping(value = "/add-student")
    public ResponseEntity<String> addStudent(@RequestBody String student){
        Student readyStudent = setStudent(student);
        studentService.addStudent(readyStudent);
        String firstName = readyStudent.getFirstName();
        return new ResponseEntity<>("Student with first name = "+firstName+" has been saved",HttpStatus.OK);
    }

    private Student setStudent(String student) {
        JSONObject jsonObj = new JSONObject(student);
        Student studentObj = new Student();
        studentObj.setFirstName(jsonObj.getString("firstName"));
        studentObj.setLastName(jsonObj.getString("lastName"));
        studentObj.setAge(jsonObj.getInt("age"));
        studentObj.setActive(jsonObj.getBoolean("active"));
        Date admissionDate = new Date(System.currentTimeMillis());
        studentObj.setAdmissionDate(admissionDate);
        return studentObj;
    }


    @GetMapping(value = "/find-student")
    public ResponseEntity<String> findStudent(@Nullable @RequestParam String firstName,String lastName,Integer age){
        List<Student> studentList = new ArrayList<>();
        JSONArray response = new JSONArray();
        response = studentService.findStudent(firstName,lastName,age);
        return new ResponseEntity<>(response.toString(),HttpStatus.OK);
    }

    @PutMapping(value = "/update-student")
    public ResponseEntity<String> updateStudent(@RequestParam String firstName, @RequestBody String student){
        Student readyStudent = setStudent(student);
        studentService.updateStudent(firstName, readyStudent);
        String name = readyStudent.getFirstName();
        return new ResponseEntity<>("Student with first name="+name+" has been updated",HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-student")
    public ResponseEntity<String> deleteStudent(@RequestParam String firstName){
        studentService.deleteStudent(firstName);
        return new ResponseEntity<>("Student with first name="+ firstName+" is deleted",HttpStatus.OK);
    }
}
