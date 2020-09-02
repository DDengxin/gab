package com.tengzhi.business.mesGz.gzck.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.mesGz.gzck.service.LyrkService;
import com.tengzhi.business.mesGz.gzck.vo.ECKOutVo;
import com.tengzhi.business.mesGz.gzda.model.GzdaVo;

@RestController
@RequestMapping("/mesGz/gzck")
public class LyrkControler {

	@Autowired
	private LyrkService LyrkService;

	@GetMapping("/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 区分模块的参数入口路径
	 * 
	 * @param paramMod
	 * @param paramType
	 * @param request
	 * @param mv
	 * @return
	 */
	@GetMapping(value = { "{cgStype}/list.html" })
	public ModelAndView toPage(@PathVariable String cgStype, HttpServletRequest request, RedirectAttributes rt,
			ModelAndView mv) {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
		mv.setView(new RedirectView(String.format("/cg/yw/purchaseFile/purchaseReceipt/%s", servletPath)));
		request.getParameterMap().forEach((key, value) -> {
			mv.addObject(key, value);
		});
		mv.addObject("cgStype", cgStype);
		// 是否定向
		mv.addObject("orient", true);
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
		return LyrkService.getSrchTopList(baseDto).getResult();
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */

	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return LyrkService.getSrchBottomList(baseDto).getResult();
	}

	/**
	 * 
	 * @description: Post新增Restful
	 */

	@PostMapping(value = "/add.html")
	public Result add(@RequestBody ECKOutVo vo) throws Exception {
		return LyrkService.save(vo);
	}

	@PutMapping(value = "/getDjFlag")
	public Result getDjFlag(@RequestBody BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		return LyrkService.getFlag(String.valueOf(map.get("outNote")), "登记");
	}

	
	
	
	@PostMapping(value = "getById")
	public Result getById(@RequestBody BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		System.out.println(map.get("outNote") +"getById");
		return Result.resultOk(LyrkService.findByInNote(baseDto));
	}


	@PutMapping(value = "add.html")
	public Result modify(@RequestBody ECKOutVo vo) throws Exception {
		return LyrkService.update(vo);

	}
	
	/**
     * 
     * @description: Post新增Restful
     */
    @PostMapping(value = "gzmj.html")
    public Result add(@RequestBody ECkInVo vo) throws Exception {
    	System.out.println("入库新增");
    	return  LyrkService.gzmj(vo);
    }

    
    
	@DeleteMapping(value = "delete/{outNote}")
	public Result delete(@PathVariable(value = "outNote") String outNote) throws Exception {
		LyrkService.deleteById(outNote);
		return Result.resultOk();
	}

	/**
	 * @throws IOException
	 * @Param: [vo]
	 * @description:采购订单选择list
	 */
	@PostMapping("/getWlbmSelectList")
	public Map<String, Object> getWlbmSelectList(BaseDto baseDto) throws IOException {
		return LyrkService.getWlbmSelectList(baseDto);
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "getSlSelectList/{outNote}")
	public List<Map> getList(@PathVariable(value = "outNote") String outNote) throws IOException {
		System.out.println("getSlSelectList");
		return LyrkService.getSlSelectList(outNote);
	}


	/**
	 * @throws IOException
	 * @Param: [vo]
	 * @description:库房下拉框
	 */
	@GetMapping("/getLib/{parent_id}/{param_type}")
	public List<Map> getLib(@PathVariable String parent_id, @PathVariable String param_type) {
		return LyrkService.getLib(parent_id, param_type);
	}

}
