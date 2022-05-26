package org.example.univer.core.dto;

import java.util.ArrayList;
import java.util.List;

public class GroupAndStudents {

    private String nameGroup;
    private List<Long> studentsId;

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
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

    @Override
    public String toString() {
        return "GroupAndStudents{" +
                "nameGroup='" + nameGroup + '\'' +
                ", studentsId=" + studentsId +
                '}';
    }
}
