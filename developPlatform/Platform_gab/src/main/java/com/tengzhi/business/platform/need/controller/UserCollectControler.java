package com.tengzhi.business.platform.need.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.need.model.G_UserCollection;
import com.tengzhi.business.platform.need.service.UserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/***
 * 用户收藏
 * 
 * @author lsh
 *
 */
@RestController
@RequestMapping("platform/need")
public class UserCollectControler {
	@Autowired
	private UserCollectService userCollectService;
	
	/**
	 * 分页查询
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchCollection")
	public Result getSrchCollection(BaseDto baseDto) throws IOException {
		return userCollectService.getSrchCollection(baseDto).getResult();
	}
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/getSrchAllCollection")
	public Result getSrchAllCollection() throws IOException {
		return userCollectService.getSrchAllCollection();
	}

	/**
	 * 新增
	 * 
	 */
	@PostMapping(value = "add")
	public Result add(@RequestBody G_UserCollection model) throws Exception {
		return userCollectService.save(model);
	}


	
	@PostMapping(value = "deleteByNote")
	public Result deleteByNote(@RequestBody String collect_url) {
		Result rs =new Result();
		rs = userCollectService.deleteByNote(collect_url);
		return rs;
	}

}
