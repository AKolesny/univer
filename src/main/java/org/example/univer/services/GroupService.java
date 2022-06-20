package org.example.univer.services;

import org.example.univer.core.dto.Group;
import org.example.univer.dao.GroupDao;
import org.example.univer.dao.api.IGroupDao;

import java.util.ArrayList;
import java.util.List;

public class GroupService {
    private static final GroupService instance = new GroupService();

    private final IGroupDao groupDao;

    private GroupService() {
        this.groupDao = GroupDao.getInstance();
    }

    public List<Group> getGroup(long id) {
        if (id == 0) {
            return groupDao.getAll();
        }

        List<Group> group = new ArrayList<>();
        group.add(groupDao.get(id));

        return group;
    }

    public long createGroup(Group group) {
        return this.groupDao.create(group);
    }

    public void updateGroup(Group group) {
        this.groupDao.update(group);
    }

    public void deleteGroup(long id) {
        this.groupDao.delete(id);
    }

    public static GroupService getInstance() {
        return instance;
    }
}
