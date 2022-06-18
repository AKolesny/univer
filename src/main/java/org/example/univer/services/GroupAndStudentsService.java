package org.example.univer.services;

import org.example.univer.core.dto.GroupAndStudents;
import org.example.univer.core.dto.groupAndStudentGet.api.IGroupAndStudentGet;
import org.example.univer.dao.GroupAndStudentDao;
import org.example.univer.core.dto.groupAndStudentGet.GroupIdOrStudentId;

public class GroupAndStudentsService {
    private static final GroupAndStudentsService instance = new GroupAndStudentsService();

    private final GroupAndStudentDao dao;

    private GroupAndStudentsService() {
        this.dao = new GroupAndStudentDao();
    }

    public IGroupAndStudentGet getGroupAndStudents(GroupIdOrStudentId gs) {
        if (gs.getStudentId() != 0) {
            return dao.getGroup(gs.getStudentId());
        } else if (gs.getGroupId() != 0) {
            return dao.getStudents(gs.getGroupId());
        } else {
            return dao.getAll();
        }
    }

    public void createGroupAndStudents(GroupAndStudents gs) {
        this.dao.create(gs);
    }

    public void deleteGroupAndStudents(GroupAndStudents gs) {
        this.dao.delete(gs);
    }

    public static GroupAndStudentsService getInstance() {
        return instance;
    }
}
