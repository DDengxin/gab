package com.tengzhi.business.cg.da.materialArchives_无处引用等待删除.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.da.materialArchives_无处引用等待删除.service.MaterialArchivesService;
import com.tengzhi.business.js.product.model.Jscpcode;

@RestController
@RequestMapping("/cg/da/materialArchives/")
@Deprecated
public class MaterialArchivesController {
	@Autowired
	private MaterialArchivesService materialArchivesService;
	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "list")
	@Log("查询产品信息")
	public Result getInboxList(BaseDto baseDto) throws IOException {
		return materialArchivesService.findAll(baseDto).getResult();
	}

	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "{id}")
	public Result getById(@PathVariable String id) {
		return Result.resultOk(materialArchivesService.findById(id));
	}

	/**
	 * 新增
	 *
	 * @return
	 */
	@PostMapping("cpcode")
	@Log("新增产品信息")
	public Result add(@RequestBody Jscpcode jscpcode) throws Exception {
		jscpcode = materialArchivesService.save(jscpcode);
		return Result.resultOk(jscpcode.getCpcodeId());
	}

	/**
	 * 修改
	 *
	 * @return
	 */
	@PutMapping("cpcode")
	@Log("修改产品信息")
	public Result modify(@RequestBody Jscpcode jscpcode) {
		materialArchivesService.update(jscpcode);
		return Result.resultOk(jscpcode.getCpcodeId());
	}

	/**
	 * 删除数据
	 *
	 *
	 * @return
	 */
	@DeleteMapping(value = "{id}")
	@Log("删除产品信息")
	public Result delete(@PathVariable(value = "id") String id) {
		materialArchivesService.deleteById(id);
		return Result.resultOk();
	}
	/**
	 * 产品分类列表
	 *
	 * @return
	 */
	@PostMapping(value = "type/treelist")
	public List<Map> getTreeList() throws IOException {
		String mod="仓库",type="库房档案";
		return materialArchivesService.getTreeList(mod,type);
	}
	@GetMapping(value = "type/checklist")
	public List<Map> getCheckList() throws IOException{
		String mod="技术",type="物料大类";
		return materialArchivesService.getCheckList(mod, type);
	}
	@GetMapping(value = "type/checkslist")
	public List<Map> getChecksList() throws IOException{
		String mod="技术",type="原料大类";
		return materialArchivesService.getCheckList(mod, type);
	}
	
	

}
