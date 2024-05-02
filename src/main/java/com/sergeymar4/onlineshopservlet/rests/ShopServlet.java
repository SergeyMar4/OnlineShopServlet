package com.sergeymar4.onlineshopservlet.rests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergeymar4.onlineshopservlet.controllers.ShopController;
import com.sergeymar4.onlineshopservlet.models.Shop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="ShopServlet", value="/shops")
public class ShopServlet extends HttpServlet {
    private ShopController shopController;

    @Override
    public void init() {
        this.shopController = new ShopController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String shopId = request.getParameter("id");

        if (shopId != null) {
            Shop shop = shopController.getById(Integer.parseInt(shopId));
            pw.println(new ObjectMapper().writeValueAsString(shop));
        }
        else {
            List<Shop> shops = shopController.getAll();
            pw.println(new ObjectMapper().writeValueAsString(shops));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTG-8");
        PrintWriter pw = response.getWriter();
        String line;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = request.getReader()) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        Shop shop = new ObjectMapper().readValue(sb.toString(), Shop.class);
        shopController.create(shop);

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

        Shop shop = new ObjectMapper().readValue(sb.toString(), Shop.class);
        shopController.update(shop);

        pw.println("<h1>Объект успешно обновлён</h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String shopId = request.getParameter("id");

        if (shopId == null) {
            pw.println("<h1>Нет информации об id</h1>");
        }
        else {
            shopController.delete(Integer.parseInt(shopId));
        }
    }
}
