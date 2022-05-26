package org.example.univer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.example.univer.core.dto.Student;
import org.example.univer.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "StudentsServlet", urlPatterns = "/student")
public class StudentsServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final StudentService service;

    public StudentsServlet() {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        service = StudentService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        //long id = mapper.readValue(req.getInputStream(), long.class);
        String json = mapper.writeValueAsString(service.getStudent());

        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        Student student = mapper.readValue(req.getInputStream(), Student.class);
        service.createStudent(student);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        long id = mapper.readValue(req.getInputStream(), Long.class);
        Student student = mapper.readValue(req.getInputStream(), Student.class);
        service.updateStudent(id, student);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        long id = mapper.readValue(req.getInputStream(), long.class);
        service.deleteStudent(id);
    }
}
