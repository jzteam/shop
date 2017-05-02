package com.mall.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.common.ResultJsonUtil;

public class UserLoginOut extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("user");
//		request.getSession().setAttribute("LoginOutMessage", "注销成功");
//		request.getRequestDispatcher("getGoodsByTypeServlet").forward(request, response);
		
		ResultJsonUtil.success(response, "注销成功");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
