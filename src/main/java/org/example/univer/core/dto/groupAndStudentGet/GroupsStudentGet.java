package org.example.univer.core.dto.groupAndStudentGet;

import org.example.univer.core.dto.Group;
import org.example.univer.core.dto.Student;

import java.util.ArrayList;
import java.util.List;

public class GroupsStudentGet {
    private Group group;
    private List<Student> students;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student a) {
        if (this.students == null) {
            this.students = new ArrayList<>();
        }

        this.students.add(a);
    }

    @Override
    public String toString() {
        return "GroupsStudentGet{" +
                "group=" + group +
                ", students=" + students +
                '}';
    }
}
