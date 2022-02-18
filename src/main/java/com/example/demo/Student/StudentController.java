package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService mStudentService;

    @Autowired
    public StudentController(StudentService studentService) {
        mStudentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return mStudentService.getStudents();
    }

    @PostMapping
    public void saveNewStudent(@RequestBody Student student) {
        mStudentService.saveNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        mStudentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        mStudentService.updateStudent(studentId, name, email);
    }
}
