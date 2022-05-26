package org.example.univer.main;

import org.example.univer.core.dto.GroupAndStudents;
import org.example.univer.dao.GroupAndStudentDao;


import java.util.ArrayList;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        GroupAndStudents student = new GroupAndStudents();
        List<Long> id = new ArrayList<>();
        id.add(1L);
        id.add(2L);
        id.add(3L);

        student.setNameGroup("sfsdg");
        student.setStudentsId(id);

        GroupAndStudentDao dao = new GroupAndStudentDao();
        //dao.create(student);

        //update();

        dao.delete(student);

        List<GroupAndStudents> students = dao.get();

        for (GroupAndStudents student1 : students) {
            System.out.println(student1);
        }
    }
}
