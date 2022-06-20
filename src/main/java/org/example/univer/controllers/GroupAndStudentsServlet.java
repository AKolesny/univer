package org.example.univer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.example.univer.core.dto.GroupAndStudents;
import org.example.univer.core.dto.groupAndStudentGet.GroupIdOrStudentId;
import org.example.univer.services.GroupAndStudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GroupAndStudentsServlet", urlPatterns = "/groupAndStudents")
public class GroupAndStudentsServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final GroupAndStudentsService service;


    public GroupAndStudentsServlet() {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        service = GroupAndStudentsService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        GroupIdOrStudentId gs = new GroupIdOrStudentId();

        try {
            gs = mapper.readValue(req.getInputStream(), GroupIdOrStudentId.class);
        } catch (MismatchedInputException ignored) {
        }

        String json = mapper.writeValueAsString(service.getGroupAndStudents(gs));
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        GroupAndStudents student = mapper.readValue(req.getInputStream(), GroupAndStudents.class);
        service.createGroupAndStudents(student);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        GroupAndStudents student = mapper.readValue(req.getInputStream(), GroupAndStudents.class);
        service.deleteGroupAndStudents(student);
    }
}
