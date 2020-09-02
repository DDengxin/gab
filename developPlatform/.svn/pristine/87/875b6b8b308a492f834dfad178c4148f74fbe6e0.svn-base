package com.tengzhi.base.jpa.result;

import com.tengzhi.base.jpa.page.BasePage;

import java.util.HashMap;
import java.util.List;

/**
 * @author lqy 控制层统一返回类
 */
public class Result extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	// 定义返回键
	public static final String KEY_CODE = "code";
	public static final String KEY_MSG = "msg";
	public static final String KEY_DATA = "data";
	public static final String KEY_TOTAL = "total";

	public Result() {
		put(KEY_CODE, 200);
	}

	/**
	 * 返回错误(500)
	 *
	 * @return
	 */
	public static Result error() {
		return resultError(ResultCode.InternalServerError, "未知异常，请联系管理员");
	}

	/**
	 * 返回错误(500)
	 *
	 * @param msg 错误信息
	 * @return
	 */
	public static Result error(String msg) {
		return resultError(ResultCode.InternalServerError, msg);
	}

	/**
	 * 返回错误
	 *
	 * @param code 状态码
	 * @param e    Exception对象
	 * @return
	 */
	public static Result error(int code, Exception e) {
		return resultError(code, e.getLocalizedMessage());
	}

	/**
	 * 返回错误(500)
	 *
	 * @param e Exception对象
	 * @return
	 */
	public static Result error(Exception e) {
		return resultError(ResultCode.InternalServerError, e.getLocalizedMessage());
	}

	/**
	 * 返回错误
	 *
	 * @param code 状态码
	 * @param msg  错误信息
	 * @return
	 */
	public static Result resultError(int code, String msg) {
		Result r = new Result();
		r.put(KEY_CODE, code);
		r.put(KEY_MSG, msg);
		return r;
	}

	public static Result resultError(Exception e) {
		Result r = new Result();
		r.put(KEY_CODE, ResultCode.InternalServerError);
		r.put(KEY_MSG, e.getMessage());
		return r;
	}

	public static Result resultError(ResultCode code, String msg) {
		return resultError(code.getCode(), msg);
	}

	/**
	 * 返回成功带消息(200)
	 *
	 * @param msg 信息
	 * @return
	 */
	public static Result resultOkMsg(String msg) {
		Result r = new Result();
		r.put(KEY_MSG, msg);
		return r;
	}

	/**
	 * 返回数据 默认状态200
	 *
	 * @param data 数据负载
	 * @param msg  信息
	 * @return
	 */
	public static Result resultOk(Object data, String msg) {
		Result r = new Result();
		r.put(KEY_DATA, data);
		r.put(KEY_MSG, msg);
		return r;
	}

	/**
	 * 返回数据 默认状态200
	 *
	 * @param data 数据负载
	 * @return
	 */
	public static Result resultOk(Object data) {
		Result r = new Result();
		r.put(KEY_DATA, data);
		return r;
	}

	/**
	 * 返回数据 默认状态200
	 *
	 * @param data 数据负载
	 * @return
	 */
	public static boolean judge(Result r) {
		if (r == null) {
			return false;
		} else if (r.get(KEY_CODE) == null) {
			return false;
		} else {
			return "200".equals(r.get(KEY_CODE).toString());
		}
	}

	/**
	 * 返回成功 默认状态200
	 *
	 * @return
	 */
	public static Result resultOk() {
		return new Result();
	}

	public static Result formPage(BasePage<?> page) {
		return resultOk().put(KEY_DATA, page.getContent()).put(KEY_TOTAL, page.getPageTotal());
	}

	public static Result formPage(List<?> list, long total) {
		return resultOk().put(KEY_DATA, list).put(KEY_TOTAL, total);
	}

	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public Result putCode(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public Result putMsg(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public static Result formPage(List<?> list) {
		return resultOk().put(KEY_DATA, list);
	}
}