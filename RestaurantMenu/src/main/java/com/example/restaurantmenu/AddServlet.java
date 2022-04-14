package com.example.restaurantmenu;

import Models.Dish;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddServlet", value = "/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestMenu");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Dish(name, price, weight, discount));
        em.getTransaction().commit();
        em.close();
        emf.close();
        response.sendRedirect("/RestaurantMenu_war/index.jsp");
    }
}
