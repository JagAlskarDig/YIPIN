package com.ypgly.yipin.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yzy
 */
@ApiModel(description= "返回响应数据")
public class GeneralResponse<T> {

   /**
     * 1表示正常，0表示异常
     */
   @ApiModelProperty(value = "是否成功,1表示正常，0表示异常")
    private String code;
    /**
     * 面向用户的消息
     */
    @ApiModelProperty(value = "面向用户的消息")
    private String message;
   /**
     * 面向开发者的详细信息
     */
   @ApiModelProperty(value = "面向用户的详细信息")
    private String detail;
    /**
     * 返回给前端的数据
     */
    @ApiModelProperty(value = "返回对象")
    private T datas;

    public GeneralResponse(String code, String message, String detail, T datas) {
        this.code = code;
        this.message = message;
        this.detail = detail;
        this.datas = datas;
    }

    public static GeneralResponse success(String message, String detail) {
        return new GeneralResponse("0", message, detail, null);
    }

    public static GeneralResponse fail(String message, String detail) {
        return new GeneralResponse("1", message, detail, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
