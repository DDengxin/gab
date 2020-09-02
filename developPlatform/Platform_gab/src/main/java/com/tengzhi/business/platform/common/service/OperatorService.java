package com.tengzhi.business.platform.common.service;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.platform.common.model.RegionVo;
import com.tengzhi.business.platform.specialist.product.model.Jscpcode;
import com.tengzhi.business.resouces.vo.SelectVo;

import java.util.List;
import java.util.Map;

public interface OperatorService extends BaseService {

	Map<String, Object> getAcademic(String reqId, String type);


	List<RegionVo> getRegion();

	Result getAllLikeSearch(String SearchKeyword);

	void digui(List<RegionVo> city);

	//获取首页的动态列
	List<Map<String, Object>> getParamValue();

	//获取用户的supplyid
	Result getUserInfo();

	/**
	 * 获取供应商下拉框数据
	 *
	 * @return
	 */
	List<Map> getSupplyCombobox();

	List<SelectVo> getProvince();

	List<SelectVo> getCity(String city);

	List<SelectVo> getArea(String city, String area);

	//查询订单
	List<Map<String, Object>> getUserOrders();

	//删除订单
	Result deleteUserOrder(String orderId);


	Jscpcode getProductById(String productId);


	//通过dataman 或 sys_user表中的user_id 获取 g_userinfo 中的user_id
	String getGuserInfoId(String dataMan);

}
