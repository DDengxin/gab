package com.tengzhi.business.platform.specialist.need.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tengzhi.base.tools.json.MapperFactory;
import com.tengzhi.business.platform.specialist.need.model.G_Need;

import java.io.IOException;
import java.util.Map;
/**
 * @author xutao
 */
public class needVo {
	private G_Need g_Need;
	private Map<String, Object> dynamic;
	private Map<String, Object> flow;
	public G_Need getG_Need() {
		return g_Need;
	}
	public void setG_Need(String g_Need) {
		JSONObject needJson = JSONObject.parseObject(g_Need);
		G_Need g_NeedObject = JSON.toJavaObject(needJson,G_Need.class);
		this.g_Need = g_NeedObject;
	}
	public Map<String, Object> getDynamic() {
		return dynamic;
	}
	public void setDynamic(String dynamic) throws IOException {
		Map<String, Object> map = (Map<String, Object>)MapperFactory.getInstance().Str2Map(dynamic);
		this.dynamic = map;
	}
	public Map<String, Object> getFlow() {
		return flow;
	}
	public void setFlow(String flow) throws IOException {
		Map<String, Object> map = (Map<String, Object>)MapperFactory.getInstance().Str2Map(flow);
		this.flow = map;
	}
}
