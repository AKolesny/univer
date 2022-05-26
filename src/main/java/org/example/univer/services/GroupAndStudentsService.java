package org.example.univer.services;

import org.example.univer.core.dto.GroupAndStudents;
import org.example.univer.core.dto.Student;

public class GroupAndStudentsService {
    private static final GroupAndStudentsService instance = new GroupAndStudentsService();

    private GroupAndStudentsService() {
    }

    public Student getGroupAndStudents(long id) {
        return null;
    }

    public void createGroupAndStudents(GroupAndStudents gs) {

    }

    public void deleteGroupAndStudents(GroupAndStudents gs) {

    }

    public static GroupAndStudentsService getInstance() {
        return instance;
    }
}
