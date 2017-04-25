package com.mall.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.mall.vo.ResultForm;

public class ResultJsonUtil {

    /**
     * 
     * 描述：返回成功
     * 
     */
    public static void success(HttpServletResponse response, Object result) throws IOException {
        ResultForm form = new ResultForm();
        form.setSuccess(1);
        form.setMessage("成功");
        form.setData(result);
        String jsonString = JSON.toJSONString(form);

        response.getWriter().write(jsonString);
    }

    /**
     * 
     * 描述：返回失败
     * 
     */
    public static void fail(HttpServletResponse response, String message) throws IOException {
        ResultForm form = new ResultForm();
        form.setSuccess(0);
        form.setMessage(message);
        String jsonString = JSON.toJSONString(form);

        response.getWriter().write(jsonString);
    }
}
