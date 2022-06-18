package org.example.univer.core.dto;

import java.util.ArrayList;
import java.util.List;

public class GroupAndStudents {

    private long groupId;
    private List<Long> studentsId;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public List<Long> getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(List<Long> studentsId) {
        this.studentsId = studentsId;
    }

    public void addId(long a) {
        if (this.studentsId == null) {
            this.studentsId = new ArrayList<>();
        }

        this.studentsId.add(a);
    }
}
