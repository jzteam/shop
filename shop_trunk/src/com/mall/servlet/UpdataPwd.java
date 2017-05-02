package com.mall.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.common.DbUtil;
import com.mall.common.ResultJsonUtil;
import com.mall.po.User;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class UpdataPwd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	 	   User user= (User) request.getSession().getAttribute("user");
	 	   if(user == null){
	 		   ResultJsonUtil.fail(response, "请先登录");
	 		   return;
	 	   }
	       String password=request.getParameter("password");
	       PreparedStatement stat=null;
	       DbUtil db=new DbUtil();
	        String sql="update tb_user set password=? where name=?";
	        try {
				stat=db.getCon().prepareStatement(sql);
				stat.setString(1, password);
				stat.setString(2, user.getName());
				stat.executeUpdate();
//				response.sendRedirect("index.jsp");
				
				ResultJsonUtil.success(response, user);
			} catch (SQLException e) {
				
				ResultJsonUtil.fail(response, "更新失败");
			}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}
