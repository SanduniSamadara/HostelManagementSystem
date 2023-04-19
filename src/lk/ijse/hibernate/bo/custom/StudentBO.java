package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.StudentDTO;

import java.util.List;

public interface StudentBO {
    String generateStudentId();

    List<StudentDTO> getAllStudents();

    boolean updateStudent(StudentDTO studentDTO);

    boolean saveStudent(StudentDTO studentDTO);

    boolean deleteStudent(String studentId);
}
