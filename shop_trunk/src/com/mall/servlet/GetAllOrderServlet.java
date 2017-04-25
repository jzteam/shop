package com.mall.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.common.ResultJsonUtil;
import com.mall.model.Model;

public class GetAllOrderServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = new Model();
        List orderList = model.getAllOrder();
        // request.setAttribute("orderList", orderList);
        // request.getRequestDispatcher("Admin/pages/manageOrder.jsp").forward(request,
        // response);

        ResultJsonUtil.success(response, orderList);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
