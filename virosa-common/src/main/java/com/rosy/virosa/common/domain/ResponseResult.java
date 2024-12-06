package com.rosy.virosa.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rosy.virosa.common.enums.HttpRespCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @param <T> Serializable: 是一个标记接口，它没有方法或字段。它的作用是告诉 Java 的序列化机制，哪些类的对象可以被序列化。
 *            JsonInclude(JsonInclude.Include.NON_NULL): 当对象的字段值为 null 时，该字段不会被序列化到 JSON 中
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {
    Integer code;
    String msg;
    T data;

    public ResponseResult() {
        this.code = HttpRespCodeEnum.SUCCESS.getCode();
        this.msg = HttpRespCodeEnum.SUCCESS.getMsg();
        this.data = null;
    }

    public static ResponseResult<Void> okResult() {
        return new ResponseResult<>();
    }

    public static <T> ResponseResult<T> okResult(T data) {
        return new ResponseResult<>(HttpRespCodeEnum.SUCCESS.getCode(), HttpRespCodeEnum.SUCCESS.getMsg(), data);
    }

    public static ResponseResult<Void> errorResult(int code, String msg) {
        return new ResponseResult<>(code, msg, null);
    }

    public static ResponseResult<Void> errorResult(HttpRespCodeEnum enums) {
        return new ResponseResult<>(enums.getCode(), enums.getMsg(), null);
    }

    public static ResponseResult<Void> errorResult(HttpRespCodeEnum enums, String msg) {
        return new ResponseResult<>(enums.getCode(), msg, null);
    }
}
