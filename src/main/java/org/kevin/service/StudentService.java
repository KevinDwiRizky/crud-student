package org.kevin.service;

import org.kevin.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(Long id, StudentDto studentDto);
    void deleteStudent(Long id);
}
