package org.example.univer.services;

import org.example.univer.core.dto.Student;
import org.example.univer.dao.StudentDao;
import org.example.univer.dao.api.IStudentDao;

import java.util.List;

public class StudentService {
    private static final StudentService instance = new StudentService();

    private final IStudentDao studentDao;

    private StudentService() {
        this.studentDao = new StudentDao();
    }

    public List<Student> getStudent() {
        return this.studentDao.get();
    }

    public void createStudent(Student student) {
        this.studentDao.create(student);
    }

    public void updateStudent(long id, Student student) {
        this.studentDao.update(id, student);
    }

    public void deleteStudent(long id) {
        this.studentDao.delete(id);
    }

    public static StudentService getInstance() {
        return instance;
    }
}
