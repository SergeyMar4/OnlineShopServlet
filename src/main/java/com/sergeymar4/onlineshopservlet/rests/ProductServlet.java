package com.sergeymar4.onlineshopservlet.rests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergeymar4.onlineshopservlet.controllers.ProductController;
import com.sergeymar4.onlineshopservlet.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="ProductServlet", value="/products")
public class ProductServlet extends HttpServlet {
    private ProductController productController;

    @Override
    public void init() {
        this.productController = new ProductController();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String productId = request.getParameter("id");

        if (productId != null) {
            Product product = productController.getById(Integer.parseInt(productId));
            pw.println(new ObjectMapper().writeValueAsString(product));
        } else {
            List<Product> products = productController.getAll();
            pw.println(new ObjectMapper().writeValueAsString(products));
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

        Product product = new ObjectMapper().readValue(sb.toString(), Product.class);
        productController.create(product);

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

        Product product = new ObjectMapper().readValue(sb.toString(), Product.class);
        productController.update(product);

        pw.println("<h1>Объект успешно обновлён</h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String productId = request.getParameter("id");

        if (productId == null) {
            pw.println("<h1>Нет информации об id</h1>");
        } else {
            productController.delete(Integer.parseInt(productId));
        }
    }
}