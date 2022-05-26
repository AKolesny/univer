package org.example.univer.services;

import org.example.univer.core.dto.Group;
import org.example.univer.dao.GroupDao;
import org.example.univer.dao.api.IGroupDao;

import java.util.List;

public class GroupService {
    private static final GroupService instance = new GroupService();

    private final IGroupDao groupDao;

    private GroupService() {
        this.groupDao = new GroupDao();
    }

    public List<Group> getGroup() {
        return groupDao.get();
    }

    public void createGroup(Group student) {
        this.groupDao.create(student);
    }

    public void updateGroup(long id, Group student) {
        this.groupDao.update(id, student);
    }

    public void deleteGroup(long id) {
        this.groupDao.delete(id);
    }

    public static GroupService getInstance() {
        return instance;
    }
}
