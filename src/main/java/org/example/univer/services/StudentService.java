package org.example.univer.services;

import org.example.univer.core.dto.Student;
import org.example.univer.dao.StudentDao;
import org.example.univer.dao.api.IStudentDao;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static final StudentService instance = new StudentService();
    private final IStudentDao studentDao;

    private StudentService() {
        this.studentDao = StudentDao.getInstance();
    }

    public List<Student> getStudent(long id) {
        if (id == 0) {
            return this.studentDao.getAll();
        }

        List<Student> students = new ArrayList<>();
        students.add(this.studentDao.get(id));

        return students;
    }

    public long createStudent(Student student) {
        return  this.studentDao.create(student);
    }

    public void updateStudent(Student student) {
        this.studentDao.update(student);
    }

    public void deleteStudent(long id) {
        this.studentDao.delete(id);
    }

    public static StudentService getInstance() {
        return instance;
    }
}
