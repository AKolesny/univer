package org.example.univer.main;

import org.example.univer.core.dto.Student;
import org.example.univer.dao.StudentDao;

import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Cаша");
        student.setAge(28);
        student.setScore(9.9);
        student.setOlympicGamer(true);

        StudentDao dao = new StudentDao();
        dao.create(student);

        //dao.update(1L, student);

        //dao.delete(3L);

        List<Student> students = dao.get();

        for (Student student1 : students) {
            System.out.println(student1);
        }
    }
}
