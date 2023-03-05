package com.geekster.ModelStudent.service;

import com.geekster.ModelStudent.model.Student;
import com.geekster.ModelStudent.repository.StudentRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student gotstudent) {
        studentRepository.save(gotstudent);
    }

    public JSONArray findStudent(String firstName, String lastName,Integer age) {
        JSONArray response = new JSONArray();
        List<Student> studentList = new ArrayList<>();
        if(firstName==null && lastName == null && age!=null)
            studentList = studentRepository.findByAge(age);
        else if(firstName!=null && lastName == null && age==null)
            studentList = studentRepository.findByFirstName(firstName);
        else if(firstName==null && lastName != null && age==null)
            studentList = studentRepository.findByLastName(lastName);
        else
            studentList = studentRepository.findAll();
        for(Student student : studentList){
            response.put(createResponse(student));
        }
        return response;
    }
    private JSONObject createResponse(Student student) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("firstName",student.getFirstName());
        jsonObj.put("lastName",student.getLastName());
        jsonObj.put("age",student.getAge());
        jsonObj.put("active",student.getActive());
        jsonObj.put("addmissionDate",student.getAdmissionDate());
        return jsonObj;
    }

    public void updateStudent(String firstName, Student newStudent) {
        List<Student> students= new ArrayList<>();
            students = studentRepository.findByFirstName(firstName);
            Student student = students.get(0);
            student.setLastName(newStudent.getLastName());
            student.setAge(newStudent.getAge());
            student.setActive(newStudent.getActive());
            Date updatedDate = new Date(System.currentTimeMillis());
            student.setAdmissionDate(updatedDate);
            studentRepository.save(student);
    }

    public void deleteStudent(String firstName) {
        studentRepository.deleteByFirstName(firstName);
    }
}
