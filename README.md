# Customer Query Model Student

- ## Frameworks and Language used
  - #### Spring Boot Framework
  - #### Java language
- ## Data Flow
  - ### SudentController
     - ##### _addStudent( )_   
     - ##### _setStudent( )_      - gets student info in the String format and returns it in the form of Student object
     - ##### _findStudent( )_
     - ##### _updateStudent( )_   
     - ##### _deleteStudent( )_     
    
    
  - ### StudentService
     - ##### _addStudent( )_      - adds a new student to tbl_student
     - ##### _findStudent( )_       - gets the list of students
     - ##### _createResponse( )_     - returns student object in the form of json object
     - ##### _updateStudent( )_     - updates student info
     - ##### _deleteStudent( )_     - soft deletes a student by changing it's active status to false
    
   - ### StudentRepository
     - ##### _findByFirstName( )_   - gets a list of students according to given first name
     - ##### _findByLastName( )_    -  gets a list of students according to given last name    
     - ##### _findByAge( )_      -  gets a list of students according to given age
     - ##### _deleteByFirstName( )_    - set the active status of a record to false to make soft delete operation
     
- ## Database Used
  - #### MySQL Database :  model_student_db
  
- ## Project Summary
  In this project we have created a model namely; Student. We have provided some endpoints and also in this section below we have given our API. Using this API we can perform the CRUD operations accordingly.
  
  
  > http://localhost:8080/api/v1/student
  
  End Points:
    - ##### /add-student    

    - ##### /find-student     

    - ##### /update-student  

    - ##### /delete-student     
