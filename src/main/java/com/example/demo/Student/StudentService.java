package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository mStudentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        mStudentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return mStudentRepository.findAll();
    }

    public void saveNewStudent(Student student) {
        Optional<Student> studentByEmail = mStudentRepository.
                findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()) {
            throw new IllegalStateException(
                    "Student already exists in the database");
        }

        mStudentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = mStudentRepository.existsById(studentId);

        if(!exists) {
            throw new IllegalStateException(
                    "Student with Id: " + studentId + " does not exist");
        }

        mStudentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = mStudentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id: " + studentId + " does not exits"
        ));

        if(name != null && name.length() > 0 &&
                !Objects.equals(student.getName(), name)) {
            student.setName(name);

        }

        if(email != null && email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {

            Optional<Student> studentOptional = mStudentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException(email + ": already exists");
            }

            student.setEmail(email);
        }
    }
}
