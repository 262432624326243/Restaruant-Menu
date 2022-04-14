package com.example.restaurantmenu;

import Models.Dish;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ViewServlet", value = "/ViewServlet")
public class ViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double from = Double.parseDouble(request.getParameter("from"));
        double to = Double.parseDouble(request.getParameter("to"));
        String dis = request.getParameter("discount");
        List<Dish> dishes = getDishList(from, to, dis);
        printDishList(dishes, response);
    }

    private void printDishList(List<Dish> dishes, HttpServletResponse response) {
        if (dishes != null && dishes.size() != 0) {
            try (PrintWriter printWriter = response.getWriter()) {
                printWriter.print("<html>");
                for (Dish d : dishes) {
                    printWriter.print(d + "</br>");
                }
                printWriter.print("</html>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (PrintWriter printWriter = response.getWriter()) {
                printWriter.print("<h1>There is no dishes</h1>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Dish> getDishList(double from, double to, String dis) {
        List<Dish> dishes;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RestMenu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = null;
        if (dis.equals("Yes")) {
            query = entityManager.createQuery("SELECT d FROM Dish d WHERE d.price>=:from AND d.price<=:to AND d.discount > 0", Dish.class);
            query.setParameter("from", from);
            query.setParameter("to", to);
        }
        if (dis.equals("No")) {
            query = entityManager.createQuery("SELECT d FROM Dish d WHERE d.price>=:from AND d.price<=:to AND d.discount = 0", Dish.class);
            query.setParameter("from", from);
            query.setParameter("to", to);
        }
        dishes = query.getResultList();
        entityManager.close();
        entityManagerFactory.close();
        return dishes;
    }
}
