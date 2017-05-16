package com.mall.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.common.ResultJsonUtil;
import com.mall.model.Model;
import com.mall.po.GoodsPager;
import com.mall.vo.Cart;

public class GetAllCartsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session = request.getSession();
    	if(session == null){
    		ResultJsonUtil.fail(response, "请先登录");
    		return;
    	}
    	
    	Cart cart = (Cart)request.getSession().getAttribute("cart");
    	
    	if(cart == null || cart.getItemCount() == 0){
    		ResultJsonUtil.success(response, "购物车里没有任何商品");
    	}else{
    		ResultJsonUtil.success(response, cart);
    	}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
