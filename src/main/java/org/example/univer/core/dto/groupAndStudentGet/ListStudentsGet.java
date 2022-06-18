package org.example.univer.core.dto.groupAndStudentGet;

import org.example.univer.core.dto.Student;
import org.example.univer.core.dto.groupAndStudentGet.api.IGroupAndStudentGet;

import java.util.ArrayList;
import java.util.List;

public class ListStudentsGet implements IGroupAndStudentGet {
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        if (this.students == null) {
            this.students = new ArrayList<>();
        }

        this.students.add(student);
    }
}
