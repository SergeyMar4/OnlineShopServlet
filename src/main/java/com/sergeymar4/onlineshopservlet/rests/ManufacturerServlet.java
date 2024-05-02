package com.sergeymar4.onlineshopservlet.rests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergeymar4.onlineshopservlet.controllers.ManufacturerController;
import com.sergeymar4.onlineshopservlet.models.Manufacturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="ManufacturerServlet", value="/manufacturers")
public class ManufacturerServlet extends HttpServlet {
    private ManufacturerController manufacturerController;

    @Override
    public void init() {
        this.manufacturerController = new ManufacturerController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String manufacturerId = request.getParameter("id");

        if (manufacturerId != null) {
            Manufacturer manufacturer = manufacturerController.getById(Integer.parseInt(manufacturerId));
            pw.println(new ObjectMapper().writeValueAsString(manufacturer));
        }
        else {
            List<Manufacturer> manufacturers = manufacturerController.getAll();
            pw.println(new ObjectMapper().writeValueAsString(manufacturers));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String line;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = request.getReader()) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        Manufacturer manufacturer = new ObjectMapper().readValue(sb.toString(), Manufacturer.class);
        manufacturerController.create(manufacturer);

        pw.println("<h1>Объект успешно создан</h1>");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String line;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = request.getReader()) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        Manufacturer manufacturer = new ObjectMapper().readValue(sb.toString(), Manufacturer.class);
        manufacturerController.update(manufacturer);

        pw.println("<h1>Объект успешно обновлён</h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String manufacturerId = request.getParameter("id");

        if (manufacturerId == null) {
            pw.println("<h1>Нет информации об id</h1>");
        } else {
            manufacturerController.delete(Integer.parseInt(manufacturerId));
        }
    }
}
