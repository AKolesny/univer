package org.example.univer.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.example.univer.core.dto.GroupAndStudents;
import org.example.univer.core.dto.groupAndStudentGet.api.IGroupAndStudentGet;
import org.example.univer.dao.GroupAndStudentDao;
import org.example.univer.core.dto.groupAndStudentGet.GroupIdOrStudentId;


import java.util.ArrayList;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        GroupAndStudents student = new GroupAndStudents();
        List<Long> id = new ArrayList<>();
        id.add(1L);
        id.add(2L);
        id.add(3L);

        student.setGroupId(4);
        student.setStudentsId(id);

        GroupAndStudentDao dao = new GroupAndStudentDao();
        //dao.create(student);

        //update();

        //dao.delete(student);

        IGroupAndStudentGet students = dao.getAll();

        /*for (GroupsStudentGet student1 : students) {
            System.out.println(student1);
        }*/

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        GroupIdOrStudentId gs = new GroupIdOrStudentId();
        gs.setGroupId(1L);

        try {
            String json = mapper.writeValueAsString(gs);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
