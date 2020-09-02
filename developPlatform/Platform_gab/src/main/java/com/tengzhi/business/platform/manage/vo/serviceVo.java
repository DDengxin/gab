package com.tengzhi.business.platform.manage.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tengzhi.base.tools.json.MapperFactory;
import com.tengzhi.business.platform.manage.model.G_ServiceManage;

import java.io.IOException;
import java.util.Map;
/**
 * @author xutao
 */
public class serviceVo {
	private G_ServiceManage g_ServiceManage;
	private Map<String, Object> dynamic;
	private Map<String, Object> flow;
	public G_ServiceManage getG_ServiceManage() {
		return g_ServiceManage;
	}
	public void setG_ServiceManage(String g_ServiceManage) {
		JSONObject serviceJson = JSONObject.parseObject(g_ServiceManage);
		G_ServiceManage g_ServiceManageObject = JSON.toJavaObject(serviceJson,G_ServiceManage.class);
		this.g_ServiceManage = g_ServiceManageObject;
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
