package com.tengzhi.business.platform.shopping.service;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.platform.shopping.model.GShopAddr;

import java.util.List;
import java.util.Map;

public interface AddrService extends BaseService {

	/**
	 * 查询登录用户的购物地址信息
	 * @return
	 */
	List<Map<String, Object>> findAllByUserId();

	/**
	 * 查询当前用户默认地址设置
	 * @return
	 */
	Result findDefaultAddress();

	/**
	 * 保存用户地址
	 * @param gShopAddr
	 * @return
	 */
	Result saveAddress(GShopAddr gShopAddr);

	/**
	 * 更新地址
	 * @param gShopAddr
	 * @return
	 */
	Result updateAddress(GShopAddr gShopAddr);
	/**
	 * 设置为默认地址
	 * @param addressNote
	 * @return
	 */
	Result setDefaultAddress(String addressNote);

	/**
	 *
	 * @param addressNote
	 * @return
	 */
	Result findAddress(String addressNote);

	/**
	 * 删除地址
	 * @param addressNote
	 * @return
	 */
	Result delAddress(String addressNote);


}
