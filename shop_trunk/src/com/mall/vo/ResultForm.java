package com.mall.vo;

public class ResultForm {
    /** 0-失败；1-成功 */
    private Integer success;
    /** 消息 */
    private String message;
    /** 数据 */
    private Object data;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
