package com.tengzhi.IM.layerIM.vo;

import java.util.List;
import java.util.Map;


public class TreeImData {

	 public Integer code;
	 public String msg;
	 public Integer count;
	 public List<Map<String, Object>> data;

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Map<String, Object>> getData() {
		return data;
	}
	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
