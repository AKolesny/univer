package org.example.univer.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.example.univer.core.dto.Group;
import org.example.univer.core.dto.Student;
import org.example.univer.services.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GroupServlet", urlPatterns = "/group")
public class GroupServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final GroupService service;

    public GroupServlet() {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        service = GroupService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        long id = mapper.readValue(req.getInputStream(), long.class);
        String json = mapper.writeValueAsString(service.getGroup());

        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        Group group = mapper.readValue(req.getInputStream(), Group.class);
        service.createGroup(group);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        long id = mapper.readValue(req.getInputStream(), Long.class);
        Group group = mapper.readValue(req.getInputStream(), Group.class);
        service.updateGroup(id, group);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        long id = mapper.readValue(req.getInputStream(), long.class);
        service.deleteGroup(id);
    }
}
