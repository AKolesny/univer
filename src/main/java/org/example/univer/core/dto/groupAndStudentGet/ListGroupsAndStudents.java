package org.example.univer.core.dto.groupAndStudentGet;

import org.example.univer.core.dto.groupAndStudentGet.api.IGroupAndStudentGet;

import java.util.ArrayList;
import java.util.List;

public class ListGroupsAndStudents implements IGroupAndStudentGet {
    private List<GroupsStudentGet> gs;

    public List<GroupsStudentGet> getGs() {
        return gs;
    }

    public void setGs(List<GroupsStudentGet> gs) {
        this.gs = gs;
    }

    public void addGroupStudent(GroupsStudentGet gs) {
        if (this.gs == null) {
            this.gs = new ArrayList<>();
        }

        this.gs.add(gs);
    }
}
