package com.mall.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.common.ResultJsonUtil;
import com.mall.model.Model;
import com.mall.po.Note;

public class AddNoteServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = (String) request.getParameter("title");
        String content = (String) request.getParameter("content");
        String author = (String) request.getParameter("author");
        String pic = (String) request.getParameter("pic");
        Note note = new Note();
        note.setTitle(title);
        note.setAuthor(author);
        note.setContent(content);
        note.setImgs(pic);
        Model model = new Model();
        if (model.addNote(note)) {// 留言成功
            String message2 = "发表留言成功";
            // request.setAttribute("message2", message2);
            // request.getRequestDispatcher("pageNoteServlet").forward(request,
            // response);

            ResultJsonUtil.success(response, message2);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
