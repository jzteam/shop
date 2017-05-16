package com.mall.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
=======
import com.mall.common.ResultJsonUtil;
>>>>>>> 72f07d975d6f1e9866413ca3fb1b7e799b08f132
import com.mall.vo.Cart;

public class DeleteItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int GoodsId = 0;
		String str_GoodsId = request.getParameter("GoodsId");
		if(str_GoodsId != null){
			GoodsId = Integer.parseInt(str_GoodsId);
		}
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		if(cart == null || cart.getItemCount() == 0){
			ResultJsonUtil.success(response, "购物车里没有任何商品");
			return;
		}
		
		cart.deleteItem(GoodsId);
//		response.sendRedirect("buyGoodsServlet");
		
		ResultJsonUtil.success(response, "删除成功");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
