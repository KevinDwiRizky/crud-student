package org.kevin.service.Impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.kevin.dto.StudentDto;
import org.kevin.entity.Student;
import org.kevin.repository.StudentRepository;
import org.kevin.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudentServiceImpl implements StudentService {

    @Inject
    StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.listAll();
        return students.stream()
                .map(student -> StudentDto.builder()
                        .name(student.getName())
                        .address(student.getAddress())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            return null;
        }
        return StudentDto.builder()
                .name(student.getName())
                .address(student.getAddress())
                .build();
    }


    @Override
    @Transactional
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = Student.builder()
                .name(studentDto.getName())
                .address(studentDto.getAddress())
                .build();

        studentRepository.persist(student);
        return StudentDto.builder()
                .name(student.getName())
                .address(student.getAddress())
                .build();
    }

    @Override
    @Transactional
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(id);
        if (existingStudent == null) {
            return null;
        }
        existingStudent.setName(studentDto.getName());
        existingStudent.setAddress(studentDto.getAddress());
        studentRepository.persist(existingStudent);
        return StudentDto.builder()
                .name(existingStudent.getName())
                .address(existingStudent.getAddress())
                .build();
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student existingStudent = studentRepository.findById(id);
        if (existingStudent != null) {
            studentRepository.delete(existingStudent);
        }
    }
}
