package com.geekster.ModelStudent.repository;

import com.geekster.ModelStudent.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select * from tbl_student where first_name=:firstName",nativeQuery = true)
    public List<Student> findByFirstName(String firstName);
    @Query(value = "select * from tbl_student where last_name=:lastName",nativeQuery = true)
    public List<Student> findByLastName(String lastName);
    @Query(value = "select * from tbl_student where age=:age",nativeQuery = true)
    public List<Student> findByAge(int age);

    @Modifying
    @Transactional
    @Query(value="update tbl_student set active=false where first_name=:firstName",countQuery = "select count(*) from tbl_student",nativeQuery = true)
    public void deleteByFirstName(String firstName);


}
