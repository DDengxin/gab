package com.tengzhi.base.jpa.result;

/**
 * 返回状态码
 */
public enum ResultCode {
    OK(200, "OK", "请求成功"),
    Created(201, "CREATED", "创建/修改成功"),

    Accepted(202, "Accepted ", "任务已接受"),
    NoContent(204, "NO CONTENT", "创建成功"),
    InvalidRequest(400, "INVALID REQUEST", "请求错误"),
    Unauthorized(401, "Unauthorized ", "没有权限"),
    Forbidden(403, "Forbidden ", "没有授权"),
    NotFound(404, "NOT FOUND", "没有找到"),
    NotAcceptable(406, "Not Acceptable", "请求格式错误"),
    Gone(410, "Gone ", "资源已被永久删除"),
    UnprocesableEntity(422, "Unprocesable entity", "资源验证错误"),
    InternalServerError(500, "INTERNAL SERVER ERROR", "服务器发生错误");

    private int code;
    private String name;
    private String msg;

    ResultCode(int code, String name, String msg) {
        code = code;
        name = name;
        msg = msg;
    }
    public int getCode(){
        return this.code;
    }


}
