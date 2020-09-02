package com.tengzhi.business.js.cpbom.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.js.cpbom.model.EJsCpcode;
import com.tengzhi.business.js.cpbom.service.CpBomService;

@RestController
@RequestMapping("/js/cpbom")
public class CpBomController {

	@Autowired
	private CpBomService cpBomService;
	
	@GetMapping("/*")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	 /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "sreach")
    public Result search_option(BaseDto baseDto) throws IOException {
        return cpBomService.sreach(baseDto).getResult();
    }
	
    /**
     * 新增数据列表
     *
     * @return
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EJsCpcode eJsCpcode) throws Exception {
    	cpBomService.save(eJsCpcode);
        return Result.resultOk(eJsCpcode.getCpcodeId());
    }
    
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody EJsCpcode eJsCpcode) throws Exception {
    	cpBomService.update(eJsCpcode);
        return Result.resultOkMsg("修改成功");
    }
    
    /**
     * 单条数据列表
     *
     * @return
     */
    @GetMapping(value = "getById/{cpcodeId}")
    public Result getById(@PathVariable(value = "cpcodeId") String cpcodeId) {
        return Result.resultOk(cpBomService.findById(cpcodeId));
    }
    
    /**
	 * 查询下表数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomList")
	public Result getSrchBottomList(BaseDto baseDto) throws IOException {
		return cpBomService.getSrchBottomList(baseDto).getResult();
	}
    
	/**
     * 单条数据列表
     *
     * @return
     */
    @GetMapping(value = "cpcodeName/{cpcodeId}")
    public Map<String,Object> cpcodeName(@PathVariable(value = "cpcodeId") String cpcodeId) {
        return cpBomService.getCpcode(cpcodeId);
    }
    
    @GetMapping(value = "delete.html/{val}")
    public Result delete(@PathVariable(value = "val") String val) throws Exception {
        String[] vals = val.split("_");
        cpBomService.delete(vals[0],vals[1],vals[2]);
    	return Result.resultOk();
    }
    
    @GetMapping(value = "deleteByBom.html/{cpcodeBom}/{cpcodeStype}/{cpcodeId}")
    public Result deleteByBom(@PathVariable(value = "cpcodeBom") String cpcodeBom,@PathVariable(value = "cpcodeStype") String cpcodeStype,@PathVariable(value = "cpcodeId") String cpcodeId) throws Exception {
        cpBomService.deleteByBom(cpcodeBom,cpcodeStype,cpcodeId);
    	return Result.resultOk();
    }
    
    /**
     * 获取单号
     *
     * @return
     * @throws Exception 
     */
    @GetMapping(value = "getBom")
    public String getBom() throws Exception {
        return cpBomService.getBom();
    }
}
