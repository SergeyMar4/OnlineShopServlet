package com.sergeymar4.onlineshopservlet.rests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergeymar4.onlineshopservlet.controllers.BasketController;
import com.sergeymar4.onlineshopservlet.controllers.CustomerController;
import com.sergeymar4.onlineshopservlet.models.Basket;
import com.sergeymar4.onlineshopservlet.models.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="BasketServlet", value="/baskets")
public class BasketServlet extends HttpServlet {
    private BasketController basketController;

    @Override
    public void init() {
        this.basketController = new BasketController();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String basketId = request.getParameter("id");

        if (basketId != null) {
            Basket basket = basketController.getById(Integer.parseInt(basketId));
            pw.println(new ObjectMapper().writeValueAsString(basket));
        }
        else {
            List<Basket> baskets = basketController.getAll();
            pw.println(new ObjectMapper().writeValueAsString(baskets));
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

        Basket basket = new ObjectMapper().readValue(sb.toString(), Basket.class);
        basketController.create(basket);

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

        Basket basket = new ObjectMapper().readValue(sb.toString(), Basket.class);
        basketController.update(basket);

        pw.println("<h1>Объект успешно обновлён</h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String basketId = request.getParameter("id");

        if (basketId == null) {
            pw.println("<h1>Нет информации об id</h1>");
        }
        else {
            basketController.delete(Integer.parseInt(basketId));
        }
    }
}
